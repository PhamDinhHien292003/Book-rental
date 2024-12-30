package com.example.book_rental.Service;

import com.example.book_rental.DTO.BookDTO;
import com.example.book_rental.DTO.CategoryDTO;
import com.example.book_rental.Entity.Books;
import com.example.book_rental.Entity.Category;
import com.example.book_rental.Repository.CategoryRepository;
import com.example.book_rental.Service.Imp.CategoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryImp {
    @Autowired
    private CategoryRepository categoryRepository;

    private BookDTO toBookDTO(Books book) {
        try{
            return new BookDTO(
                    book.getId(),                        // ID
                    book.getIsbn(),                      // ISBN
                    book.getName(),                      // Tên sách
                    book.getImg(),                       // Ảnh
                    book.getAuthor(),                    // Tác giả
                    book.getMrp(),                       // Giá gốc
                    book.getSecurity(),                  // Security
                    book.getRent(),                      // Giá thuê
                    book.getQty(),                       // Số lượng
                    book.getBestSeller(),                // Best seller
                    book.getShortDesc(),                 // Mô tả ngắn
                    book.getDescription(),                // Mô tả chi tiết
                    book.getStatus(),                    // Trạng thái
                    book.getPrice()
            );
        }catch (Exception ex){
            ex.printStackTrace();
            return null;

        }
    }

    private CategoryDTO toCategoryDTO(Category category) {
        List<BookDTO> lst = new ArrayList<>();
        category.getBooks().forEach(b -> lst.add(toBookDTO(b)));
        return  new CategoryDTO(
                category.getId(),
                category.getCategory(),
                category.getStatus(),
                lst
        );
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
       for (Category category : categoryRepository.findAll()) {
           categoryDTOList.add(toCategoryDTO(category));
       }
       return categoryDTOList;
    }

    @Override
    public CategoryDTO getAllbookbycate(int id) {
        CategoryDTO cate  ;
        if(categoryRepository.findByid(id) != null){
            cate = toCategoryDTO(categoryRepository.findByid(id));
        }
        else {
            cate = null;
        }
        return cate;
    }
}
