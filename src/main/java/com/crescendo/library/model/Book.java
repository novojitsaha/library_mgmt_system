package com.crescendo.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotNull(message = "The \"isAvailable\" field is required")
    private Boolean isAvailable;

    // default constructor
    public Book() {}

    public Book(Long id, String title, String author, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
