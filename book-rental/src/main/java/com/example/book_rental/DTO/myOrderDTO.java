package com.example.book_rental.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class myOrderDTO {
    private int orderId;
    private Date orderDate;
    private String bookName;
    private double price;
    private Date orderTime;
    private String paymentMethod;
    private String paymentStatus;
    private String statusName;

    public myOrderDTO(int orderId, Date orderDate, String bookName, double price, Date orderTime, String paymentMethod, String paymentStatus, String statusName) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.bookName = bookName;
        this.price = price;
        this.orderTime = orderTime;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.statusName = statusName;
    }

    public myOrderDTO() {
    }


    // Getters and Setters
}