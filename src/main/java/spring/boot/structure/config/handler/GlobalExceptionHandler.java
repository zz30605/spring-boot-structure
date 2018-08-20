package spring.boot.structure.config.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleApplicationException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ResponseEntity<String> handle(HttpClientErrorException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseBody
    public ResponseEntity<String> handle(HttpServerErrorException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        JsonObject jsonObject;
        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
            List<FieldError> error = processFieldErrors(result.getFieldErrors());
            jsonObject = createResponseBody(ex, status, request, Optional.of(error));
        } else {
            jsonObject = createResponseBody(ex, status, request);
        }
        log.error(ex.getMessage(), ex);
        return super.handleExceptionInternal(ex, jsonObject.toString(), headers, status, request);
    }

    private JsonObject createResponseBody(Exception ex, HttpStatus status, WebRequest request) {
        return createResponseBody(ex, status, request, Optional.empty());
    }

    private JsonObject createResponseBody(Exception ex, HttpStatus status, WebRequest request,
                                          Optional<List<FieldError>> error) {
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        jsonObject.addProperty("timestamp", System.currentTimeMillis());
        jsonObject.addProperty("status", status.value());
        if (error.isPresent()) {
            jsonObject.add("message", gson.toJsonTree(error));
        } else {
            jsonObject.addProperty("message", ex.getMessage());
        }
        jsonObject.addProperty("exception", ex.getClass().toString());
        jsonObject.addProperty("error", status.getReasonPhrase());
        jsonObject.addProperty("path", request.getDescription(false));
        return jsonObject;
    }

    private List<FieldError> processFieldErrors(List<FieldError> fieldErrors) {
        List<FieldError> ourFieldErrors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            FieldError error = new FieldError(fieldError.getObjectName(), fieldError.getField(),
                    fieldError.getDefaultMessage());
            ourFieldErrors.add(error);
        }
        return ourFieldErrors;
    }

}
