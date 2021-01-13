package com.kodilla.project.service;

import com.kodilla.project.domain.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private RestTemplate restTemplate = new RestTemplate();

    public List<UserDto> getUsers() throws HttpServerErrorException {
        UserDto[] allUsers = restTemplate.getForObject("http://localhost:8080/v1/user", UserDto[].class);

        if(allUsers != null) {
            return Arrays.asList(allUsers);
        } else {
            return Arrays.asList(new UserDto[0]);
        }
    }

    public List<UserDto> getUsersByFirstname(String firstname) throws HttpClientErrorException {
        UserDto[] allUsers = restTemplate.getForObject("http://localhost:8080/v1/user/name/" + firstname, UserDto[].class);

        if(allUsers != null) {
            return Arrays.asList(allUsers);
        } else {
            return Arrays.asList(new UserDto[0]);
        }
    }

    public UserDto getUserById(final Long id) throws HttpServerErrorException {
        return restTemplate.getForObject("http://localhost:8080/v1/user/id/" + id, UserDto.class);
    }

    public void createUser(final UserDto userDto) throws HttpServerErrorException {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user")
                .build().encode().toUri();
        try {
            restTemplate.postForObject(url, userDto, UserDto.class);
        } catch (HttpClientErrorException e) {
            System.out.println("This name probably exists");
        }    }

    public void updateUser(final UserDto userDto) throws HttpServerErrorException {
        restTemplate.put("http://localhost:8080/v1/user", userDto, UserDto.class);
    }

    public void deleteUser(final Long id) throws HttpServerErrorException {
        restTemplate.delete("http://localhost:8080/v1/user/" + id);
    }
}