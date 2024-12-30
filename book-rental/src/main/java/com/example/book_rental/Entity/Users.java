package com.example.book_rental.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name= "name")
    private String name ;

    @Column(name= "email")
    private String email ;

    @Column(name = "mobile")
    private String tel ;

    @Column(name = "doj")
    private Date doj ;

    @Column(name = "password")
    private String password ;

    @OneToMany(mappedBy = "user")
    private List<Orders> books ;


    public Users() {
    }

    public Users(String name, String email, String tel, Date doj, String password) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.doj = doj;
        this.password = password;
    }

    public Users(int id, String name, String email, String tel, Date doj, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.doj = doj;
        this.password = password;
    }

    public Users(int id, String email, String tel, Date doj, String password) {
        this.id = id;
        this.email = email;
        this.tel = tel;
        this.doj = doj;
        this.password = password;
    }

    public Users(int id, String email, String tel, Date doj, String password, List<Orders> books) {
        this.id = id;
        this.email = email;
        this.tel = tel;
        this.doj = doj;
        this.password = password;
        this.books = books;
    }

    public Users(int id, String name, String email, String tel, Date doj, String password, List<Orders> books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.doj = doj;
        this.password = password;
        this.books = books;
    }
}
