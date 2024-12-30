package com.example.book_rental.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "contact_us")
public class Contact_us {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", length = 70)
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    // Constructor
    public Contact_us() {
    }




}