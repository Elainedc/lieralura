package com.alura.literalura.service;

import com.alura.literalura.client.GutendexClient;
import com.alura.literalura.model.Book;
import com.alura.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GutendexClient gutendexClient;

    public Book searchBookByTitle(String title) {
        Book book = gutendexClient.searchBookByTitle(title);
        if (book != null) {
            bookRepository.save(book);
        }
        return book;
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public List<Book> listTop10Books() {
        return bookRepository.findTop10ByOrderByDownloadsDesc();
    }
}

