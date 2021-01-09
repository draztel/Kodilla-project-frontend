package com.kodilla.project.service;

import com.kodilla.project.domain.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private RestTemplate restTemplate = new RestTemplate();

    public List<UserDto> getUsers() throws HttpServerErrorException {
        List<UserDto> allUsers = restTemplate.getForObject("http://localhost:8080/v1/user", List.class);

        if(allUsers != null) {
            return allUsers;
        } else {
            return new ArrayList<>();
        }
    }

    public List<UserDto> getUsersByFirstname(String firstname) throws HttpClientErrorException {
        List<UserDto> allUsers = restTemplate.getForObject("http://localhost:8080/v1/user/name/" + firstname, List.class);

        if(allUsers != null) {
            return allUsers;
        } else {
            return new ArrayList<>();
        }
    }

    public UserDto getUserById(final Long id) throws HttpServerErrorException {
        UserDto userDto = restTemplate.getForObject("http://localhost:8080/v1/user/id/" + id, UserDto.class);

        if(userDto != null) {
            return userDto;
        } else {
            return new UserDto();
        }
    }

    public void createUser(final UserDto userDto) throws HttpServerErrorException {
        restTemplate.postForObject("http://localhost:8080/v1/user", userDto, UserDto.class);
    }

    public void updateUser(final UserDto userDto) throws HttpServerErrorException {
        restTemplate.put("http://localhost:8080/v1/user", userDto, UserDto.class);
    }

    public void deleteUser(final Long id) throws HttpServerErrorException {
        restTemplate.delete("http://localhost:8080/v1/user/" + id);
    }
}