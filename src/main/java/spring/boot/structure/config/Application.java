package spring.boot.structure.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
@EnableConfigurationProperties
@EnableJpaAuditing
public class Application {


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
