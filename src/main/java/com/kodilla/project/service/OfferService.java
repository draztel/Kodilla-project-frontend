package com.kodilla.project.service;

import com.kodilla.project.domain.dto.OfferDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class OfferService {
    private static OfferService offerServiceInstance = null;
    private RestTemplate restTemplate = new RestTemplate();

    private OfferService() {
    }

    public static OfferService getInstance() {
        if(offerServiceInstance == null) {
            synchronized (OfferService.class) {
                if(offerServiceInstance == null) {
                    offerServiceInstance = new OfferService();
                }
            }
        }
        return offerServiceInstance;
    }

    public List<OfferDto> getOffers() throws HttpServerErrorException {
        OfferDto[] allOffers = restTemplate.getForObject("http://localhost:8080/v1/offer", OfferDto[].class);

        if(allOffers != null) {
            return Arrays.asList(allOffers);
        } else {
            return Arrays.asList(new OfferDto[0]);
        }
    }

    public List<OfferDto> getOffersByName(String name) throws HttpClientErrorException {
        OfferDto[] allOffers = restTemplate.getForObject("http://localhost:8080/v1/offer/name/" + name, OfferDto[].class);

        if(allOffers != null) {
            return Arrays.asList(allOffers);
        } else {
            return Arrays.asList(new OfferDto[0]);
        }
    }

    public OfferDto getOfferById(final Long id) throws HttpServerErrorException {
        return restTemplate.getForObject("http://localhost:8080/v1/offer/id/" + id, OfferDto.class);
    }

    public void createOffer(final OfferDto offerDto) throws HttpServerErrorException {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/offer")
                .build().encode().toUri();
        try {
            restTemplate.postForObject(url, offerDto, OfferDto.class);
        } catch (HttpClientErrorException e) {
            System.out.println("This name probably exists");
        }
    }

    public void updateOffer(final OfferDto offerDto) throws HttpServerErrorException {
        restTemplate.put("http://localhost:8080/v1/offer", offerDto, OfferDto.class);
    }

    public void deleteOffer(final Long id) throws HttpServerErrorException {
        restTemplate.delete("http://localhost:8080/v1/offer/" + id);
    }
}
