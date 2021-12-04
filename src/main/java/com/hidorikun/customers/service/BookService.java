package com.hidorikun.customers.service;

import com.hidorikun.customers.model.dto.BookDTO;
import com.hidorikun.customers.model.dto.CustomerDTO;
import com.hidorikun.customers.model.entity.Customer;
import com.hidorikun.customers.repository.CustomerRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private final RestTemplate restTemplate;

    private final String urlBase = "http://localhost:7100/api/v1/";

    public BookService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BookDTO getBook(long bookId) {
        String url = urlBase + "book/{bookId}";
        ResponseEntity<BookDTO> response = this.restTemplate.getForEntity(url, BookDTO.class, bookId);

        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public List<BookDTO> getBooks() {
        String url = urlBase + "/books";

        ResponseEntity<BookDTO[]> response = this.restTemplate.getForEntity(url, BookDTO[].class);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }

        return new ArrayList<>();
    }

    public List<BookDTO> getBooks(List<Long> bookIds) {
        String url = urlBase + "/books";

        ResponseEntity<BookDTO[]> response = this.restTemplate.postForEntity(url, bookIds, BookDTO[].class);

        if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }

        return new ArrayList<>();
    }

    public BookDTO addBook(BookDTO bookDTO) {
        String url = urlBase + "/book";

        return this.restTemplate.postForEntity(url, bookDTO, BookDTO.class).getBody();
    }

    public void updateBook(long bookId, BookDTO bookDTO) {
        String url = urlBase + "book/" + bookId;

        this.restTemplate.put(url, bookDTO);
    }

    public void removeBook(@PathVariable long bookId) {
        String url = urlBase + "book/" + bookId;

        this.restTemplate.delete(url, bookId);
    }

}
