package com.kodilla.project.service;

import com.kodilla.project.domain.dto.JokeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {
    private RestTemplate restTemplate = new RestTemplate();

    public JokeDto getRandomJoke() {
        JokeDto jokeDto = restTemplate.getForObject("http://localhost:8080/v1/joke/random", JokeDto.class);
        return jokeDto;
    }
}
