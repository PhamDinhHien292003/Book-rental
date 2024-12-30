package com.example.book_rental.Repository;

import com.example.book_rental.Entity.Books;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
    public Books findByid(int id);

    @Query("UPDATE Books set qty = :qty where id = :id")
    public void updateQty(@Param("qty") int qty, @Param("id") int id);
}
