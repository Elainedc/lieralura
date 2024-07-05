package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String language;
    private int downloads;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public void setTitle(String title) {
    }

    public void setLanguage(String languages) {
    }

    public void setDownloads(int downloadCount) {
    }

    public void setAuthorName(String name) {
    }

    // Getters and setters
}

