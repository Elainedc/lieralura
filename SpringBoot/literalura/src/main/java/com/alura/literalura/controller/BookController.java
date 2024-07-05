package com.alura.literalura.controller;

import com.alura.literalura.model.Book;
import com.alura.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public Book searchBookByTitle(@RequestParam String title) {
        return bookService.searchBookByTitle(title);
    }

    @GetMapping
    public List<Book> listAllBooks() {
        return bookService.listAllBooks();
    }

    @GetMapping("/language")
    public List<Book> listBooksByLanguage(@RequestParam String language) {
        return bookService.listBooksByLanguage(language);
    }

    @GetMapping("/top10")
    public List<Book> listTop10Books() {
        return bookService.listTop10Books();
    }
}

