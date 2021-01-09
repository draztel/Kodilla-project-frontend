package com.kodilla.project.service;

import com.kodilla.project.domain.dto.GameDto;
import com.kodilla.project.domain.dto.MovieDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private RestTemplate restTemplate = new RestTemplate();

    public List<MovieDto> getmovies() throws HttpServerErrorException {
        List<MovieDto> allMovies = restTemplate.getForObject("http://localhost:8080/v1/movies", List.class);

        if(allMovies != null) {
            return allMovies;
        } else {
            return new ArrayList<>();
        }
    }

    public MovieDto getMovie(final Long id) throws HttpServerErrorException {
        MovieDto movieDto = restTemplate.getForObject("http://localhost:8080/v1/movies/" + id, MovieDto.class);

        if(movieDto != null) {
            return movieDto;
        } else {
            return new MovieDto();
        }
    }

    public void createMovie(final MovieDto movieDto) throws HttpServerErrorException {
        restTemplate.postForObject("http://localhost:8080/v1/games", movieDto, MovieDto.class);
    }

    public void updateMovie(final MovieDto movieDto) throws HttpServerErrorException {
        restTemplate.put("http://localhost:8080/v1/movies", movieDto, MovieDto.class);
    }

    public void deleteMovie(final Long id) throws HttpServerErrorException {
        restTemplate.delete("http://localhost:8080/v1/movies/" + id);
    }
}
