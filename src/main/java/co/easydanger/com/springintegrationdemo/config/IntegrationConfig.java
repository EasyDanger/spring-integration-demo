package co.easydanger.com.springintegrationdemo.config;

import co.easydanger.com.springintegrationdemo.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.MessageChannel;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {

    @Bean
    public MessageChannel recieverChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel replyChannel() {
        return new DirectChannel();
    }

    @Bean @Transformer(inputChannel = "integration.student.gateway.channel", outputChannel = "integration.student.toConvertObject.channel")
    public HeaderEnricher enrichHeader() {
        Map<String, HeaderValueMessageProcessor<String>> headersToAdd = new HashMap<>();
        headersToAdd.put("Header1", new StaticHeaderValueMessageProcessor<String>("Test Header #1"));
        headersToAdd.put("Header2", new StaticHeaderValueMessageProcessor<String>("Test Header #2"));
        HeaderEnricher enricher = new HeaderEnricher(headersToAdd);
        return enricher;
    }


    @Bean
    @Transformer(inputChannel = "integration.student.toConvertObject.channel", outputChannel = "integration.student.objectToJson.channel")
    public ObjectToJsonTransformer objectToJsonTransformer() {
        return new ObjectToJsonTransformer(getMapper());
    }

    @Bean
    public Jackson2JsonObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonObjectMapper(mapper);
    }

    @Bean
    @Transformer(inputChannel = "integration.student.jsonToObject.channel", outputChannel = "integration.student.jsonToObject.fromTransformer.channel")
    JsonToObjectTransformer jsonToObjectTransformer() {
        return new JsonToObjectTransformer(Student.class);
    }

}
