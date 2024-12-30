package com.example.book_rental.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user ;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "pin")
    private int pin;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "total")
    private int total;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "order_status")
    private int orderStatus;

    @Column(name = "date")
    private Date date;

    @Column(name = "duration")
    private int duration;



    public Orders( Users user ,  String address, String address2, int pin, String paymentMethod, int total, String paymentStatus, int orderStatus, Date date, int duration) {
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
        this.user = user;
    }


    public Orders() {
    }

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", pin=" + pin +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", total=" + total +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", orderStatus=" + orderStatus +
                ", date=" + date +
                ", duration=" + duration +
                ", user=" + (user != null ? user.getId() : "null") +
                '}';
    }


}
