package com.hidorikun.customers.service;

import com.hidorikun.customers.model.dto.BorrowingDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BorrowingService {

    private final RestTemplate restTemplate;

    private final String urlBase = "http://localhost:7300/api/v1/";

    public BorrowingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<BorrowingDTO> getBorrowingsByCustomers(long customerId) {
        String url = urlBase + "borrowings/{customerId}";
        ResponseEntity<BorrowingDTO[]> response = this.restTemplate.getForEntity(url, BorrowingDTO[].class, customerId);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }

        return new ArrayList<>();
    }

    public BorrowingDTO borrowBook(BorrowingDTO borrowingDTO) {
        String url = urlBase + "/borrowings";

        return this.restTemplate.postForEntity(url, borrowingDTO, BorrowingDTO.class).getBody();
    }

    public BorrowingDTO returnBook(BorrowingDTO borrowingDTO) {
        String url = urlBase + "borrowings/return";

        return this.restTemplate.postForEntity(url, borrowingDTO, BorrowingDTO.class).getBody();
    }

}
