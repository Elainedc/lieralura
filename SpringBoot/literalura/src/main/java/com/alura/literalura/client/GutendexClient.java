package com.alura.literalura.client;

import com.alura.literalura.model.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/";

    public Book searchBookByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "?title=" + title;
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response);
            JsonNode bookNode = root.path("results").get(0);
            Book book = new Book();
            book.setTitle(bookNode.path("title").asText());
            book.setLanguage(bookNode.path("languages").get(0).asText());
            book.setDownloads(bookNode.path("download_count").asInt());
            // Assumindo que o autor é o primeiro da lista
            JsonNode authorNode = bookNode.path("authors").get(0);
            book.setAuthorName(authorNode.path("name").asText());
            return book;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

