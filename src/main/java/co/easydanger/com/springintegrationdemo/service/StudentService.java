package co.easydanger.com.springintegrationdemo.service;

import co.easydanger.com.springintegrationdemo.model.Student;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class StudentService {

    @ServiceActivator(inputChannel = "integration.student.objectToJson.channel", outputChannel = "integration.student.jsonToObject.channel")
    public Message<?> recieveMessage(Message<?> message) throws MessagingException {
        System.out.println("#######HERE_COMES_A_MESSAGE#######");
        System.out.println(message);
        System.out.println("#######HERE_COMES_SOME_JSON#######");
        System.out.println("Object to Json - " + message.getPayload());
        return message;
    }

    @ServiceActivator(inputChannel = "integration.student.jsonToObject.fromTransformer.channel")
    public void processingJsonToObject(Message<?> message) throws MessagingException {
        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
        MessageBuilder.fromMessage(message);
        System.out.println("#######HERE_COMES_AN_OBJECT#######");
        System.out.println("Json to Object - " + message.getPayload());
        Student student = (Student) message.getPayload();
        Message<?> newMessage = MessageBuilder.withPayload(student.toString()).build();
        replyChannel.send(newMessage);
    }

}
