package com.example.book_rental.Repository;

import com.example.book_rental.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin findByEmail(String emails);
}
