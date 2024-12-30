package com.example.book_rental.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "sliderbar")
public class Sliderbar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img", columnDefinition = "TEXT")
    private String img;

    @Column(name = "header", columnDefinition = "TEXT")
    private String header;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    public Sliderbar() {
    }

    public Sliderbar(Long id, String img, String header, String content) {
        this.id = id;
        this.img = img;
        this.header = header;
        this.content = content;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}