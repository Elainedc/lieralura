package com.alura.literalura.controller;

import com.alura.literalura.model.Author;
import com.alura.literalura.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> listAllAuthors() {
        return authorService.listAllAuthors();
    }

    @GetMapping("/alive")
    public List<Author> listAuthorsAliveInYear(@RequestParam int year) {
        return authorService.listAuthorsAliveInYear(year);
    }

    @GetMapping("/search")
    public Author searchAuthorByName(@RequestParam String name) {
        return authorService.searchAuthorByName(name);
    }
}

