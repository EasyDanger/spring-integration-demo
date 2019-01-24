package co.easydanger.com.springintegrationdemo.controller;

import co.easydanger.com.springintegrationdemo.model.Student;
import co.easydanger.com.springintegrationdemo.service.IntegrationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrate")
public class IntegrationController {

    @Autowired
    private IntegrationGateway integrationGateway;

    @GetMapping(value = "{name}")
    public String getMessageFromIntegrationService(@PathVariable("name") String name) {
        return integrationGateway.sendMessage(name);
    }

    @PostMapping
    public String processStudentDetails(@RequestBody Student student) {
        return integrationGateway.processStudentDetails(student);
    }

}
