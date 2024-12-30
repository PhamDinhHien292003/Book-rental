package com.example.book_rental.Service.Imp;

import com.example.book_rental.DTO.BookDTO;
import com.example.book_rental.DTO.OrderDTO;
import com.example.book_rental.DTO.myOrderDTO;
import com.example.book_rental.Entity.Books;
import com.example.book_rental.Entity.Users;

import java.sql.Date;
import java.util.List;

public interface OrderImp {
    public int addOrder(Users idUser, String adr , String adr2 , int pin , String payment , int total , String paymentStatus , int orderStatus , Date date , int duration , BookDTO book  );
    public List<Object[]> allListOfUser(String gmail);
    public boolean deleteOrder(int id);
}
