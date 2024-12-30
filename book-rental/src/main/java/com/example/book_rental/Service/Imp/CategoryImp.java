package com.example.book_rental.Service.Imp;

import com.example.book_rental.DTO.CategoryDTO;

import java.util.List;

public interface CategoryImp {
    public List<CategoryDTO> getAllCategory();
    public CategoryDTO getAllbookbycate(int id );
}
