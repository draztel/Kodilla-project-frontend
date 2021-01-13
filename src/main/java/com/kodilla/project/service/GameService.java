package com.kodilla.project.service;

import com.kodilla.project.domain.dto.GameDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {
    private RestTemplate restTemplate = new RestTemplate();

    public List<GameDto> getGames() throws HttpServerErrorException {
        GameDto[] allGames = restTemplate.getForObject("http://localhost:8080/v1/game", GameDto[].class);

        if(allGames != null) {
            return Arrays.asList(allGames);
        } else {
            return Arrays.asList(new GameDto[0]);
        }
    }

    public List<GameDto> getGamesByName(String name) throws HttpClientErrorException {
        GameDto[] allGames = restTemplate.getForObject("http://localhost:8080/v1/game/name/" + name, GameDto[].class);

        if(allGames != null) {
            return Arrays.asList(allGames);
        } else {
            return Arrays.asList(new GameDto[0]);
        }
    }

    public GameDto getGameById(final Long id) throws HttpServerErrorException {
        return restTemplate.getForObject("http://localhost:8080/v1/game/id/" + id, GameDto.class);
    }

    public void createGame(final GameDto gameDto) throws HttpServerErrorException {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/game")
                .build().encode().toUri();
        try {
            restTemplate.postForObject(url, gameDto, GameDto.class);
        } catch (HttpClientErrorException e) {
            System.out.println("This name probably exists");
        }    }

    public void updateGame(final GameDto gameDto) throws HttpServerErrorException {
        restTemplate.put("http://localhost:8080/v1/game", gameDto, GameDto.class);
    }

    public void deleteGame(final Long id) throws HttpServerErrorException {
        restTemplate.delete("http://localhost:8080/v1/game/" + id);
    }
}
