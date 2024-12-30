package com.example.book_rental.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category")
    private String category;


    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "category")
    private List<Books> books;

    public Category(String category) {
        this.category = category;
    }

    public Category(int id, String category, int status, List<Books> books) {
        this.id = id;
        this.category = category;
        this.status = status;
        this.books = books;
    }

    public Category() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}
