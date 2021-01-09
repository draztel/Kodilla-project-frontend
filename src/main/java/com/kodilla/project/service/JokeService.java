package com.kodilla.project.service;

import com.kodilla.project.domain.dto.JokeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class JokeService {
    private RestTemplate restTemplate = new RestTemplate();

    public JokeDto getRandomJoke() {
        JokeDto jokeDto = new JokeDto("id", "type", "setup", "punchline");
        return jokeDto;
    }
}
