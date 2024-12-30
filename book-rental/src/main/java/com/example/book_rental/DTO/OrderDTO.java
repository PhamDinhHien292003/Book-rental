package com.example.book_rental.DTO;

import java.util.Date;

public class OrderDTO {
    private int id;
    private String address;
    private String address2;
    private int pin;
    private String paymentMethod;
    private int total;
    private String paymentStatus;
    private int orderStatus;
    private Date date;
    private int duration;
    private int userId; // Chỉ định userId thay vì đối tượng Users

    public OrderDTO() {
    }

    public OrderDTO(int id, String address, String address2, int pin, String paymentMethod, int total,
                    String paymentStatus, int orderStatus, Date date, int duration, int userId) {
        this.id = id;
        this.address = address;
        this.address2 = address2;
        this.pin = pin;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
        this.date = date;
        this.duration = duration;
        this.userId = userId; // Thêm userId vào DTO
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}