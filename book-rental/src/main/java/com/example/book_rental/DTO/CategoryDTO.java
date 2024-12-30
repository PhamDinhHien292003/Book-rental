package com.example.book_rental.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CategoryDTO {

    private int id;
    private String category;
    private int status;
    private List<BookDTO> bookIds; // Danh sách ID của các sách thuộc danh mục này

    // Constructor
    public CategoryDTO() {
    }

    public CategoryDTO(int id, String category, int status, List<BookDTO> bookIds) {
        this.id = id;
        this.category = category;
        this.status = status;
        this.bookIds = bookIds;
    }

    public CategoryDTO(int id, String category, int status) {
        this.status = status;
        this.category = category;
        this.id = id;
    }


}