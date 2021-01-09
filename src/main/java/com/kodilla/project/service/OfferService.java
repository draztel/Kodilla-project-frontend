package com.kodilla.project.service;

import com.kodilla.project.domain.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OfferService {
    private static OfferService offerServiceInstance = null;

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

    private RestTemplate restTemplate = new RestTemplate();

    public List<OfferDto> getOffers() throws HttpServerErrorException {
        List<OfferDto> allOffers = restTemplate.getForObject("http://localhost:8080/v1/offers", List.class);

        if(allOffers != null) {
            return allOffers;
        } else {
            return new ArrayList<>();
        }
    }

    public OfferDto getOffer(final Long id) throws HttpServerErrorException {
        OfferDto offerDto = restTemplate.getForObject("http://localhost:8080/v1/offers/" + id, OfferDto.class);

        if(offerDto != null) {
            return offerDto;
        } else {
            return new OfferDto();
        }
    }

    public void createOffer(final OfferDto offerDto) throws HttpServerErrorException {
        restTemplate.postForObject("http://localhost:8080/v1/offers", offerDto, OfferDto.class);
    }

    public void updateOffer(final OfferDto offerDto) throws HttpServerErrorException {
        restTemplate.put("http://localhost:8080/v1/offers", offerDto, OfferDto.class);
    }

    public void deleteOffer(final Long id) throws HttpServerErrorException {
        restTemplate.delete("http://localhost:8080/v1/offers/" + id);
    }
}
