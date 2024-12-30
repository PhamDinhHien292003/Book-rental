package com.example.book_rental.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private  String password;

}
