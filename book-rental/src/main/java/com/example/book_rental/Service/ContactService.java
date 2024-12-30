package com.example.book_rental.Service;


import com.example.book_rental.Entity.Contact_us;
import com.example.book_rental.Repository.ContactRepository;
import com.example.book_rental.Service.Imp.ContactImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ContactService implements ContactImp {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public boolean addContact(String name, String email, String mobile, String msg) {
        Contact_us contact_us = new Contact_us();
        contact_us.setName(name);
        contact_us.setEmail(email);
        contact_us.setMobile(mobile);
        contact_us.setMessage(msg);
        try {
            Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            contact_us.setDate(date);
            contactRepository.save(contact_us);
            return true ;
        }catch (Exception ex){
            ex.printStackTrace();
            return  false;
        }
    }
}
