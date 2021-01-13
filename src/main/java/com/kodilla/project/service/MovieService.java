package com.kodilla.project.service;

import com.kodilla.project.domain.dto.MovieDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
    private RestTemplate restTemplate = new RestTemplate();

    public List<MovieDto> getMovies() throws HttpServerErrorException {
        MovieDto[] allMovies = restTemplate.getForObject("http://localhost:8080/v1/movie", MovieDto[].class);

        if(allMovies != null) {
            return Arrays.asList(allMovies);
        } else {
            return Arrays.asList(new MovieDto[0]);
        }
    }

    public List<MovieDto> getMoviesByName(String name) throws HttpClientErrorException {
        MovieDto[] allMovies = restTemplate.getForObject("http://localhost:8080/v1/movie/name/" + name, MovieDto[].class);

        if(allMovies != null) {
            return Arrays.asList(allMovies);
        } else {
            return Arrays.asList(new MovieDto[0]);
        }
    }

    public MovieDto getMovieById(final Long id) throws HttpServerErrorException {
        return restTemplate.getForObject("http://localhost:8080/v1/movie/id/" + id, MovieDto.class);
    }

    public void createMovie(final MovieDto movieDto) throws HttpServerErrorException {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/movie")
                .build().encode().toUri();
        try {
            restTemplate.postForObject(url, movieDto, MovieDto.class);
        } catch (HttpClientErrorException e) {
            System.out.println("This name probably exists");
        }    }

    public void updateMovie(final MovieDto movieDto) throws HttpServerErrorException {
        restTemplate.put("http://localhost:8080/v1/movie", movieDto, MovieDto.class);
    }

    public void deleteMovie(final Long id) throws HttpServerErrorException {
        restTemplate.delete("http://localhost:8080/v1/movie/" + id);
    }
}
