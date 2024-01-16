package com.client.service;

import com.client.dto.ClimateDto;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;
import java.util.Map;

public interface ClimatService {

    public Map<String, List<ClimateDto>> getAllMeteoBy()  throws IOException, ConnectException;
    public ClimateDto getLastDateClimateDtoForGivenVille(String ville) throws IOException;
}
