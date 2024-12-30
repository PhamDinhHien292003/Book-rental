package com.example.book_rental.Repository;

import com.example.book_rental.Entity.Order_details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<Order_details , Long> {
}
