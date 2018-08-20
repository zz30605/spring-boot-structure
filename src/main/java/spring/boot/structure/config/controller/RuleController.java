//package spring.boot.structure.config.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import net.funpodium.services.traffic.dto.CheckRuleResource;
//import net.funpodium.services.traffic.dto.RuleResource;
//import net.funpodium.services.traffic.entity.Rule;
//import net.funpodium.services.traffic.services.TrafficService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Slf4j
//@Validated
//@RestController
//@Profile("dev")
//public class RuleController {
//
//    @Autowired
//    private TrafficService service;
//
//    //check for the input request
//    @RequestMapping(method = RequestMethod.POST, value = "/check-rule")
//    public ResponseEntity<String> checkRule(@Valid @RequestBody CheckRuleResource checkRuleResource) {
//        //log user info
//        log.info(String.format("checkRule (%s, %s, %s, %s, %s)", checkRuleResource.getIp(), checkRuleResource.getBrand(), checkRuleResource.getCompany(), checkRuleResource.getLocale(), checkRuleResource.getPlatform()));
//
//        if (!service.checkRule(checkRuleResource)) {
//            log.info("Access Denied");
//            //if the ipAddress can not be identified then return error message
//            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
//        }
//        //if the ipAddress exists then return success
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/rules")
//    public List<RuleResource> getRules() {
//        log.info("getRules");
//        return RuleResource.ruleResourcesConverter(service.getAllRule());
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/companies/{company}/brands/{brand}/rules")
//    public ResponseEntity<List<RuleResource>> getRule(@PathVariable("company") String company, @PathVariable("brand") String brand) {
//        log.info(String.format("getRule (%s, %s)", company, brand));
//        if (service.getRuleByCompanyAndBrand(company, brand).isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(RuleResource.ruleResourcesConverter(service.getRuleByCompanyAndBrand(company, brand)), HttpStatus.OK);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/rules")
//    public ResponseEntity<String> addRule(@Valid @RequestBody RuleResource ruleResource) {
//        log.info(String.format("addRule (%s, %s, %s, %s, %s, %d)", ruleResource.getBrand(), ruleResource.getCompany(), ruleResource.getCountry(), ruleResource.getLocale(), ruleResource.getPlatform(), ruleResource.getId()));
//        Rule rule = new Rule(ruleResource);
//        log.info(ruleResource.toString());
//        if (!service.addRule(rule)) {
//            log.error(ruleResource.toString());
//            //if the data already existed
//            return new ResponseEntity<>("Already Existed", HttpStatus.CONFLICT);
//        }
//        //if we add data successfully
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/rules/{id}")
//    public ResponseEntity<String> updateRule(@Valid @RequestBody RuleResource ruleResource, @PathVariable("id") Long id) {
//        log.info(String.format("updateRule (%s, %s, %s, %s, %s, %d)", ruleResource.getBrand(), ruleResource.getCompany(), ruleResource.getCountry(), ruleResource.getLocale(), ruleResource.getPlatform(), ruleResource.getId()));
//        Rule rule = new Rule(id, ruleResource);
//        if (!service.updateRule(rule)) {
//            log.info(rule.toString());
//            //if the data can not be identified then return error message
//            return new ResponseEntity<>("Could Not Found", HttpStatus.NOT_FOUND);
//        }
//        //if we delete data successfully
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/rules/{id}")
//    public ResponseEntity<String> deleteRule(@PathVariable("id") Long id) {
//        log.info(String.format("deleteRule (%d)", id));
//        if (!service.deleteRule(id)) {
//            log.info("Could not found");
//            //if the data can not be identified then return error message
//            return new ResponseEntity<>("Could Not Found", HttpStatus.NOT_FOUND);
//        }
//        //if we delete data successfully
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//    }
//
//
//}