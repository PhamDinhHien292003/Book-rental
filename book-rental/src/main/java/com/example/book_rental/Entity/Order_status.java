package com.example.book_rental.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_status")
public class Order_status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", length = 100)
    private String statusName;

    // Constructor
    public Order_status() {
    }

    public Order_status(String statusName) {
        this.statusName = statusName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}