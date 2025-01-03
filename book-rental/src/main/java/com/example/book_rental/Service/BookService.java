package com.example.book_rental.Service;

import com.example.book_rental.DTO.BookDTO;
import com.example.book_rental.DTO.CategoryDTO;
import com.example.book_rental.Entity.Books;
import com.example.book_rental.Entity.Category;
import com.example.book_rental.Repository.BooksRepository;
import com.example.book_rental.Repository.CategoryRepository;
import com.example.book_rental.Service.Imp.BookImp;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookImp {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryDTO toCategoryDTO(Category category) {
        return  new CategoryDTO(
                category.getId(),
                category.getCategory(),
                category.getStatus()
        );
    }

    private BookDTO toBookDTO(Books book) {
        return new BookDTO(
                book.getId(),                        // ID
                toCategoryDTO(book.getCategory()),                         // Danh mục
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
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for(Category category : categoryRepository.findAll()){
            categoryDTOList.add(this.toCategoryDTO(category));
        }

        for(Books book : booksRepository.findAll()){
            bookDTOList.add(toBookDTO(book));
        }
        return bookDTOList;
    }






    @Override
    public List<BookDTO> getAllBookByCategory(int category) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (BookDTO bookDTO: getAllBook() ){
            if (bookDTO.getCategoryId().getId() == category){
                bookDTOList.add(bookDTO);
            }
        }
        return bookDTOList;
    }

    @Override
    public BookDTO getBookById(int id) {
        return toBookDTO(booksRepository.findByid(id));
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> lstCate = new ArrayList<>();
        try{
            categoryRepository.findAll().forEach(
                  val ->  lstCate.add(toCategoryDTO(val) )
            );
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return lstCate;
    }

    @Override
    public boolean handle(int id, String method) {
        switch (method){
            case "active" : {
                try{
                    Optional<Category> category = categoryRepository.findById(id);
                    if(category.isPresent()){
                        category.get().setStatus(1);
                        categoryRepository.save(category.get());
                        return true ;
                    }

                }catch (Exception ex ){

                }
                break ;
            }

            case "deactive" : {
                try{
                    Optional<Category> category = categoryRepository.findById(id);
                    if(category.isPresent()){
                        category.get().setStatus(0);
                        categoryRepository.save(category.get());
                        return true ;
                    }

                }catch (Exception ex ){

                }
                break ;
            }

            case "del" : {
                try{
                    Optional<Category> category = categoryRepository.findById(id);
                    if(category.isPresent()){
                        categoryRepository.deleteById(id);
                        return true ;
                    }

                }catch (Exception ex ){

                }
                break ;
            }
        }
        return false;
    }

    @Override
    public CategoryDTO getCateByID(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return toCategoryDTO(category.get());
        }
        return null ;
    }

    @Override
    public boolean renameCategory(int id, String name) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            category.get().setCategory(name);
            categoryRepository.save(category.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean addCategory(String name) {
       Category category = categoryRepository.findByCategory(name);
        if(category == null){
            categoryRepository.save(new Category(name));
            return true;
        }
        return false;
    }

    @Override
    public boolean editBook(int id, String ISBN, int category_id, String name, String author, int mrp, int security, int rent, Integer qty, MultipartFile img, String short_desc, String description) {
        Books books = booksRepository.findByid(id);
        books.setIsbn(ISBN);
        Optional<Category> category = categoryRepository.findById(category_id);
        if(category.isPresent()){
            books.setCategory(category.get());
            books.setName(name);
            books.setAuthor(author);
            books.setMrp(mrp);
            books.setSecurity(security);
            books.setRent(rent);
            books.setQty(qty);
            if(img != null)books.setImg(img.getOriginalFilename());
            books.setShortDesc(short_desc);
            books.setDescription(description);
            booksRepository.save(books);
            return true;
        }
        return false;
    }

    @Override
    public boolean addBook(String ISBN, int category_id, String name, String author, int mrp, int security, int rent, Integer qty, MultipartFile img, String short_desc, String description) {
        Optional<Category> category = categoryRepository.findById(category_id);
        if (category.isPresent()) {
            String imgFileName = img.getOriginalFilename(); // Lấy tên file hình ảnh
            // Nếu cần, bạn có thể lưu hình ảnh vào thư mục và lấy đường dẫn
            // Ví dụ: String imgPath = saveImage(img);

            // Tạo đối tượng Books với các tham số đã cho
            Books books = new Books(
                    category.get(), // Lấy Category từ Optional
                    ISBN,
                    name,
                    imgFileName, // Hoặc imgPath nếu bạn đã lưu hình ảnh
                    author,
                    mrp,
                    security,
                    rent,
                    qty,
                    0, // bestSeller, có thể thay đổi theo logic của bạn
                    short_desc,
                    description,
                    true // status, có thể thay đổi theo logic của bạn
            );

            // Giả sử bạn có một phương thức lưu sách
            booksRepository.save(books); // Lưu sách vào cơ sở dữ liệu
            return true;
        }
        return false; // Trả về false nếu không tìm thấy danh mục
    }

    @Override
    public boolean bookEdit(int id, String method) {
        Books book = booksRepository.findByid(id);
        switch (method){
            case "deactive" : {
                if(book != null){
                    book.setBestSeller(0);
                    booksRepository.save(book);
                    return true ;
                }
                break ;
            }

            case "active" : {
                if(book != null){
                    book.setBestSeller(1);
                    booksRepository.save(book);
                    return true ;
                }
                break ;

            }
            case "del" : {
                if(book != null){
                    booksRepository.deleteById(id);
                    return true ;
                }
                break ;

            }

        }
        return false ;
    }


}
