package com.example.book_rental.Repository;

import com.example.book_rental.Entity.Contact_us;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact_us , Integer> {

}
