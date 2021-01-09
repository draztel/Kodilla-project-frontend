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

    public List<JokeDto> getTenRandomJokes() {
        List<JokeDto> jokeDtoList = new ArrayList<>();
        jokeDtoList.add(new JokeDto("1", "type", "setup", "punchline"));
        jokeDtoList.add(new JokeDto("2", "type", "setup", "punchline"));
        jokeDtoList.add(new JokeDto("3", "type", "setup", "punchline"));
        jokeDtoList.add(new JokeDto("4", "type", "setup", "punchline"));
        jokeDtoList.add(new JokeDto("5", "type", "setup", "punchline"));
        jokeDtoList.add(new JokeDto("6", "type", "setup", "punchline"));
        jokeDtoList.add(new JokeDto("7", "type", "setup", "punchline"));
        jokeDtoList.add(new JokeDto("8", "type", "setup", "punchline"));
        jokeDtoList.add(new JokeDto("9", "type", "setup", "punchline"));
        jokeDtoList.add(new JokeDto("10", "type", "setup", "punchline"));
        return jokeDtoList;
    }
}
