package co.easydanger.com.springintegrationdemo.service;

import co.easydanger.com.springintegrationdemo.model.Student;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface IntegrationGateway {

    @Gateway(requestChannel = "integration.gateway.channel")
    public String sendMessage(String message);

    @Gateway(requestChannel = "integration.student.gateway.channel")
    public String processStudentDetails(Student student);

}
