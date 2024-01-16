package com.client.service;

import com.client.client.CliamteClimateImpl;
import com.client.client.ClimateClient;
import com.client.dto.ClimateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.exception.UserNotFoundException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.ConnectException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClimateServiceImpl implements ClimatService{

    private final ClimateClient climateClient;
    private String receivedMessage;


    @KafkaListener(topics = "CodeDecodeTopic", groupId = "codedecode-group")
    public void listenToCodeDecodeKafkaTopic(String messageReceived) {
        receivedMessage = messageReceived;
        System.out.println("Message received is " + receivedMessage);
    }

    @Override
    public Map<String, List<ClimateDto>> getAllMeteoBy() throws IOException, ConnectException {
        WebClient.Builder builder= WebClient.builder();

        Map<String, List<ClimateDto>> map= new LinkedHashMap<>();
        try {
            map= CliamteClimateImpl.convertJsonToMap(receivedMessage);
            log.info("result: {}", map);
        }catch (ConnectException e) {
            log.error("Error converting JSON to Map", e);
            map= Collections.emptyMap();
            log.info("result error: {}", map);
        }
        return map;
    }

    @Override
    public ClimateDto getLastDateClimateDtoForGivenVille(String ville) throws IOException {
        Map<String, List<ClimateDto>> map= getAllMeteoBy();
        if(map== null){
           throw new  UserNotFoundException("Waiting ");
        }

        List<ClimateDto> climateList = map.get(ville.toLowerCase());

        if (climateList == null || climateList.isEmpty()) {
            throw new UserNotFoundException("Not found");
        }
        return climateList.stream()
                .max(Comparator.comparing(ClimateDto::getCreatedAt))
                .orElseThrow(()-> new UserNotFoundException("Not found"));
    }


}
