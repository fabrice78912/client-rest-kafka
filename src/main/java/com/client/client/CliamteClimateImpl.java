package com.client.client;


import com.client.dto.ClimateDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class CliamteClimateImpl implements ClimateClient{

    @Value("${url.metoe}")
    private String meteoUrl;

    private final KafkaTemplate<String, String> kafkaTemplate;


//    @Scheduled(cron = "0 */1 * * * *", zone = "America/Montreal") // Run every 1 minutes
//    @Override
//    public Map<String, List<ClimateDto>> getMetoByVille() throws IOException, ConnectException {
//        WebClient.Builder builder= WebClient.builder();
//
//        Map<String, List<ClimateDto>> map= new LinkedHashMap<>();
//        try {
//            String block = builder.build()
//                    .get()
//                    //.uri(meteoUrl + "api/v1/meteo/climate/list?ville=" + ville + "&page="+page+"&size="+size)
//                    .uri(meteoUrl + "/api/v1/meteo/all")
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block();
//
//            map=convertJsonToMap(block);
//            log.info("result: {}", map);
//        }catch (ConnectException e) {
//            log.error("Error converting JSON to Map", e);
//            map= Collections.emptyMap();
//            log.info("result error: {}", map);
//        }
//        return map;
//    }


   // @Scheduled(cron = "0 */1 * * * *", zone = "America/Montreal") // Run every 1 minutes
    @Override
    public Map<String, List<ClimateDto>> getMetoByVille(String ville) throws IOException {
      /*  WebClient.Builder builder= WebClient.builder();

        String block = builder.build()
                .get()
                //.uri(meteoUrl + "api/v1/meteo/climate/list?ville=" + ville + "&page="+page+"&size="+size)
                .uri(meteoUrl + "api/v1/meteo/climate/list?ville=Berlin&page=0&size=1")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Map<String, List<ClimateDto>> map= new LinkedHashMap<>();

        try {
            map=convertJsonToMap(block);
            log.info("result: {}", map);
        } catch (IOException e) {
            log.error("Error converting JSON to Map", e);
            map= Collections.emptyMap();
            log.info("result error: {}", map);
        }*/
        return null;
    }


    //Gett All meteo and send on kafka
    @Scheduled(cron = "0 */1 * * * *", zone = "America/Montreal") // Run every 1 minutes
    @Override
    public String getMetoByVilleString() throws IOException {
        WebClient.Builder builder= WebClient.builder();

        String block = builder.build()
                .get()
                //.uri(meteoUrl + "api/v1/meteo/climate/list?ville=" + ville + "&page="+page+"&size="+size)
                .uri(meteoUrl + "/api/v1/meteo/all")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        //SEND MESSAGE ON KAFKA
        kafkaTemplate.send("CodeDecodeTopic", block);
        System.out.println("Message: "+block);
        log.info("result: {}", block);
        return block;
    }



    public static Map<String, List<ClimateDto>> convertJsonToMap(String json) throws IOException {

        if (json == null) {
            throw new UserNotFoundException("JSON content is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        // Use TypeReference to specify the target type (Map<String, List<Climate>>)
        return objectMapper.readValue(json, new TypeReference<>() {});
    }



}
