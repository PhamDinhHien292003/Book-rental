package com.example.book_rental.Repository;

import com.example.book_rental.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category findByid(int name);
    public Category findByCategory(String name);
}
