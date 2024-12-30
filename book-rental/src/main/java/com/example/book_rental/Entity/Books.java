package com.example.book_rental.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "ISBN", length = 20)
    private String isbn;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "img", length = 255)
    private String img;

    @Column(name = "author", length = 75)
    private String author;

    @Column(name = "mrp")
    private Integer mrp;

    @Column(name = "security")
    private Integer security;

    @Column(name = "rent")
    private Integer rent;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "best_seller")
    private Integer bestSeller;

    @Column(name = "short_desc", length = 200)
    private String shortDesc;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "status")
    private Boolean status; // Assuming status is a boolean

    @Column(name = "price", insertable = false, updatable = false)
    private int price;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }

    public Integer getSecurity() {
        return security;
    }

    public void setSecurity(Integer security) {
        this.security = security;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(Integer bestSeller) {
        this.bestSeller = bestSeller;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Books(Long id, Category category, String isbn, String name, String img, String author, Integer mrp, Integer security, Integer rent, Integer qty, Integer bestSeller, String shortDesc, String description, Boolean status, int price) {
        this.id = id;
        this.category = category;
        this.isbn = isbn;
        this.name = name;
        this.img = img;
        this.author = author;
        this.mrp = mrp;
        this.security = security;
        this.rent = rent;
        this.qty = qty;
        this.bestSeller = bestSeller;
        this.shortDesc = shortDesc;
        this.description = description;
        this.status = status;
        this.price = price;
    }

    public Books(Category category, String isbn, String name, String img, String author, Integer mrp, Integer security, Integer rent, Integer qty, Integer bestSeller, String shortDesc, String description, Boolean status) {
        this.category = category;
        this.isbn = isbn;
        this.name = name;
        this.img = img;
        this.author = author;
        this.mrp = mrp;
        this.security = security;
        this.rent = rent;
        this.qty = qty;
        this.bestSeller = bestSeller;
        this.shortDesc = shortDesc;
        this.description = description;
        this.status = status;
    }

    public Books() {
    }


}
