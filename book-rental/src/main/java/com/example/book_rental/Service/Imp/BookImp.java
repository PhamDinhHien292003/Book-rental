package com.example.book_rental.Service.Imp;

import com.example.book_rental.DTO.BookDTO;
import com.example.book_rental.DTO.CategoryDTO;

import java.util.List;

public interface BookImp {
    public List<BookDTO> getAllBook();
    public List<BookDTO> getAllBookByCategory(int category);
    public BookDTO getBookById(int id);
    public List<CategoryDTO> getAllCategory();
    public boolean handle(int id , String method );
    public CategoryDTO getCateByID(int id );
    public boolean renameCategory(int id , String name);
    public boolean addCategory(String name);
}
