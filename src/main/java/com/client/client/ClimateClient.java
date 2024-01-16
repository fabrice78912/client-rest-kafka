package com.client.client;

import com.client.dto.ClimateDto;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;
import java.util.Map;

public interface ClimateClient {

    //public Map<String, List<ClimateDto>> getMetoByVille() throws IOException;

    public Map<String, List<ClimateDto>> getMetoByVille(String ville) throws IOException;


    public String getMetoByVilleString() throws IOException, ConnectException;
}
