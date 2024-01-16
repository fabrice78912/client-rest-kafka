package com.client.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {


//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final ClimateClient climateClient;
//
//    @Scheduled(cron = "0 */1 * * * *", zone = "America/Montreal")
//    public void sendMessageToTopic() throws IOException {
//        String message= climateClient.getMetoByVilleString();
//        kafkaTemplate.send("CodeDecodeTopic", message);
//        System.out.println("Message: "+message);
//    }
}
