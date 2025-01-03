package com.example.book_rental.Repository;

import com.example.book_rental.DTO.myOrderDTO;
import com.example.book_rental.Entity.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders , Integer> {
    @Transactional
    @Modifying
    @Query(value = "SELECT o.id, o.date, b.name, od.price, od.time, o.payment_method, o.payment_status, os.status_name " +
            "FROM orders o " +
            "JOIN order_detail od ON o.id = od.order_id " +
            "JOIN books b ON b.id = od.book_id " +
            "JOIN order_status os ON o.order_status = os.id " +
            "JOIN users u ON u.id = o.user_id " +
            "WHERE u.email = :email", nativeQuery = true)
    List<Object[]> findOrdersByEmail(String email);


    public void deleteById(int id);


    @Transactional
    @Modifying
    @Query(value = "SELECT o.id, o.date, b.name, od.price, od.time, CONCAT(o.address, ',', o.address2) AS address, " +
            "o.payment_method, o.payment_status, os.status_name " +
            "FROM orders o " +
            "JOIN order_detail od ON o.id = od.order_id " + // Corrected to use od.order_id
            "JOIN books b ON b.id = od.book_id " +
            "JOIN order_status os ON os.id = o.order_status " +
            "ORDER BY o.date DESC", nativeQuery = true)
    List<Object[]> getAllOrder();
}
