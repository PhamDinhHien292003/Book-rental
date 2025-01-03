package com.example.book_rental.Service.Imp;

import com.example.book_rental.Entity.Contact_us;

import java.util.List;

public interface ContactImp {
    public boolean addContact(String name , String email , String mobile , String msg);
    public List<Contact_us> getAllContact();
    public boolean deleteContact(int id);
}
