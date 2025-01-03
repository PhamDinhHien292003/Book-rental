package com.example.book_rental.Repository;

import com.example.book_rental.Entity.Order_details;
import com.example.book_rental.Entity.Order_status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Order_StatusRepository extends JpaRepository<Order_status,Integer> {
}
