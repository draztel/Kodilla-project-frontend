package com.kodilla.project.service;

import com.kodilla.project.domain.dto.GameDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private RestTemplate restTemplate = new RestTemplate();

    public List<GameDto> getGames() throws HttpServerErrorException {
        List<GameDto> allGames = restTemplate.getForObject("http://localhost:8080/v1/game", List.class);

        if(allGames != null) {
            return allGames;
        } else {
            return new ArrayList<>();
        }
    }

    public List<GameDto> getGamesByName(String name) throws HttpClientErrorException {
        List<GameDto> allGames = restTemplate.getForObject("http://localhost:8080/v1/game/name/" + name, List.class);

        if(allGames != null) {
            return allGames;
        } else {
            return new ArrayList<>();
        }
    }

    public GameDto getGameById(final Long id) throws HttpServerErrorException {
        GameDto gameDto = restTemplate.getForObject("http://localhost:8080/v1/game/" + id, GameDto.class);

        if(gameDto != null) {
            return gameDto;
        } else {
            return new GameDto();
        }
    }

    public void createGame(final GameDto gameDto) throws HttpServerErrorException {
        restTemplate.postForObject("http://localhost:8080/v1/game", gameDto, GameDto.class);
    }

    public void updateGame(final GameDto gameDto) throws HttpServerErrorException {
        restTemplate.put("http://localhost:8080/v1/game", gameDto, GameDto.class);
    }

    public void deleteGame(final Long id) throws HttpServerErrorException {
        restTemplate.delete("http://localhost:8080/v1/game/" + id);
    }
}
