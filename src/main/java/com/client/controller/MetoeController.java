package com.client.controller;

import com.client.dto.ClimateDto;
import com.client.service.ClimatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class MetoeController {

    private final ClimatService climatService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, List<ClimateDto>>> getAllClimateByVilleOrderByCreatedDatei() throws IOException {
        return new ResponseEntity<>(climatService.getAllMeteoBy(), HttpStatus.OK);}


    @GetMapping("/last")
    public ResponseEntity<ClimateDto> getLastMeteoByVille(@RequestParam("ville") String ville) throws IOException {
        return new ResponseEntity<>(climatService.getLastDateClimateDtoForGivenVille(ville), HttpStatus.OK);
    }
}
