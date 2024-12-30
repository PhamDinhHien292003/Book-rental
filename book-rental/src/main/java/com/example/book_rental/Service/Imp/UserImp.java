package com.example.book_rental.Service.Imp;

import com.example.book_rental.DTO.UserDTO;

public interface UserImp {
    public boolean checkLogin(String email, String password);

    public UserDTO getUserInfomation(String email);

    public boolean editProfile(String name, String email, String phone, String password);

    public String signUp(String name ,String email, String phone, String password);
}