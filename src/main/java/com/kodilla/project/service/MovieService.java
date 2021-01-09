package com.kodilla.project.service;

import com.kodilla.project.domain.dto.GameDto;
import com.kodilla.project.domain.dto.MovieDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private RestTemplate restTemplate = new RestTemplate();

    public List<MovieDto> getMovies() throws HttpServerErrorException {
        List<MovieDto> allMovies = restTemplate.getForObject("http://localhost:8080/v1/movie", List.class);

        if(allMovies != null) {
            return allMovies;
        } else {
            return new ArrayList<>();
        }
    }

    public List<MovieDto> getMoviesByName(String name) throws HttpClientErrorException {
        List<MovieDto> allMovies = restTemplate.getForObject("http://localhost:8080/v1/movie/name/" + name, List.class);

        if(allMovies != null) {
            return allMovies;
        } else {
            return new ArrayList<>();
        }
    }

    public MovieDto getMovie(final Long id) throws HttpServerErrorException {
        MovieDto movieDto = restTemplate.getForObject("http://localhost:8080/v1/movie/" + id, MovieDto.class);

        if(movieDto != null) {
            return movieDto;
        } else {
            return new MovieDto();
        }
    }

    public void createMovie(final MovieDto movieDto) throws HttpServerErrorException {
        restTemplate.postForObject("http://localhost:8080/v1/movie", movieDto, MovieDto.class);
    }

    public void updateMovie(final MovieDto movieDto) throws HttpServerErrorException {
        restTemplate.put("http://localhost:8080/v1/movie", movieDto, MovieDto.class);
    }

    public void deleteMovie(final Long id) throws HttpServerErrorException {
        restTemplate.delete("http://localhost:8080/v1/movie/" + id);
    }
}
