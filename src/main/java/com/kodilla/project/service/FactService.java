package com.kodilla.project.service;

import com.kodilla.project.domain.dto.FactDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class FactService {
    private RestTemplate restTemplate = new RestTemplate();

    public FactDto getRandomFact() throws HttpClientErrorException {
        FactDto factDto = new FactDto("random fact");
        return factDto;
    }
}
