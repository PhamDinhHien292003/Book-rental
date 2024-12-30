package com.example.book_rental.DTO;

public class BookDTO {

    private Long id;
    private CategoryDTO categoryId;
    private String isbn;
    private String name;
    private String img;
    private String author;
    private Integer mrp;
    private Integer security;
    private Integer rent;
    private Integer qty;
    private Integer bestSeller;
    private String shortDesc;
    private String description;
    private Boolean status;
    private Integer price;

    // Constructor
    public BookDTO() {

    }

    public BookDTO(Long id, CategoryDTO categoryId, String isbn, String name, String img, String author, Integer mrp, Integer security, Integer rent, Integer qty, Integer bestSeller, String shortDesc, String description, Boolean status, Integer price) {
        this.id = id;
        this.categoryId = categoryId;
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

    public BookDTO(Long id, String isbn, String name, String img, String author, Integer mrp, Integer security, Integer rent, Integer qty, Integer bestSeller, String shortDesc, String description, Boolean status, Integer price) {
        this.id = id;
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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDTO getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryDTO categoryId) {
        this.categoryId = categoryId;
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
}