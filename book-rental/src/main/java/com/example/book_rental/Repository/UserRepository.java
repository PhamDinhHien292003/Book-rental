package com.example.book_rental.Repository;

import com.example.book_rental.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    public Optional<Users> findByEmail(String email);

}
