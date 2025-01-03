package com.example.book_rental.Service;

import com.example.book_rental.Entity.Order_status;
import com.example.book_rental.Repository.Order_StatusRepository;
import com.example.book_rental.Service.Imp.OrderStatusImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusService implements OrderStatusImp {
    @Autowired
    Order_StatusRepository order_statusRepository;


    @Override
    public List<Order_status> getAllStatus() {
        return new ArrayList<>(order_statusRepository.findAll());
    }
}
