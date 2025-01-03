package com.example.book_rental.Service.Imp;

import com.example.book_rental.DTO.BookDTO;
import com.example.book_rental.DTO.CategoryDTO;
import org.springframework.web.multipart.MultipartFile;

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
    public boolean bookEdit(int id , String method );
    public boolean editBook(
            int id,
            String ISBN,
            int category_id,
            String name,
            String author,
            int mrp,
            int security,
            int rent,
            Integer qty,
            MultipartFile img,
            String short_desc,
            String description);

    public boolean addBook(String ISBN,
                           int category_id,
                           String name,
                           String author,
                           int mrp,
                           int security,
                           int rent,
                           Integer qty,
                           MultipartFile img,
                           String short_desc,
                           String description);
}
