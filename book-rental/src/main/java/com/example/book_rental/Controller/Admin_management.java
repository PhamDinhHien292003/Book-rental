package com.example.book_rental.Controller;


import com.example.book_rental.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Admin")
public class Admin_management {

    @Autowired
    private BookService bookService;

    @Autowired
    AdminService adminService;

    @Autowired
    OrderService orderService ;

    @Autowired
    OrderStatusService orderStatusService ;
    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;

    @GetMapping("Login")
    public String login(HttpSession session) {
        if(session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "Admin/login";
    }

    @PostMapping("Login")
    public String handleLogin(@RequestParam String email , @RequestParam String password , Model model , HttpSession session) {
        if(adminService.checkLogin(email, password)) {
            session.setAttribute("admin", email);
            model.addAttribute("category" , bookService.getAllCategory());
            return "redirect:/Admin/index";
        }
        model.addAttribute("message", "Invalid email or password");
        return "redirect:/Admin/index";
    }

    @GetMapping("index")
    public String index( Model model  ) {
        model.addAttribute("category" , bookService.getAllCategory());
        return "Admin/index";
    }

    @GetMapping("handleCate")
    public String handleCate(@RequestParam int id , @RequestParam String method , Model model , HttpSession session) {
        if(bookService.handle(id, method)){
            model.addAttribute("category" , bookService.getAllCategory());
            return "redirect:/Admin/index";
        }
        else {
            session.removeAttribute("admin");
            return "Admin/login";
        }
    }

    @GetMapping("editCate")
    public String editCate(@RequestParam  int id , Model model  ) {
        model.addAttribute("category" , bookService.getCateByID(id));
        return "Admin/editCate";
    }

    @PostMapping("editCate")
    public String editCate(@RequestParam  int id , @RequestParam String category,  Model model  ) {
        if(bookService.renameCategory(id ,category)){
            model.addAttribute("category" , bookService.getAllCategory());
            return "redirect:/Admin/index";
        }
        else {
            model.addAttribute("res", "Invalid category");
            return "Admin/editCate";
        }
    }

    @GetMapping("addCate")
    public String addCate(@RequestParam(required = false ,defaultValue = "")  String category , Model model  ) {
        if(!category.isEmpty() ){
            if( bookService.addCategory(category) ) {
                model.addAttribute("category", bookService.getAllCategory());

                return "redirect:/Admin/index";
            }
            else {
                model.addAttribute("res", "category already exist");
            }
        }
        return "Admin/addCate";
    }

    @GetMapping("bookManager")
    public String book(Model model  ) {
        model.addAttribute("books" , bookService.getAllBook());
        return "Admin/Book";
    }

    @GetMapping("handleBook")
    public String handleBook(Model model , @RequestParam int id , @RequestParam String method   ) {
        if(bookService.bookEdit(id, method)){
            model.addAttribute("books" , bookService.getAllBook());
            return "redirect:/Admin/bookManager";
        }
        return "redirect:/Admin/Login";
    }

    @GetMapping("updateBook")
    public String updateBook(
            @RequestParam int id,
            @RequestParam(required = false, defaultValue = "") String ISBN,
            @RequestParam(required = false, defaultValue = "") Integer category_id,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String author,
            @RequestParam(required = false, defaultValue = "0") int mrp,
            @RequestParam(required = false, defaultValue = "0") int security,
            @RequestParam(required = false, defaultValue = "0") int rent,
            @RequestParam(required = false, defaultValue = "0") Integer qty,
            @RequestParam(required = false) MultipartFile img,
            @RequestParam(required = false, defaultValue = "") String short_desc,
            @RequestParam(required = false, defaultValue = "") String description , Model model){

        if(name.isEmpty()){
            model.addAttribute("book", bookService.getBookById(id));
            model.addAttribute("category", bookService.getAllCategory());
            return "Admin/editBook";
        }
        else {
            if(bookService.editBook(id, ISBN, category_id, name, author, mrp, security, rent, qty, img , short_desc, description)){
                model.addAttribute("books" , bookService.getAllBook());
                return "redirect:/Admin/bookManager";
            }
            return "redirect:/Admin/Login";
        }
    }

    @GetMapping("addBook")
    public String addBook(Model model ) {
        model.addAttribute("categories" , bookService.getAllCategory());
        return "Admin/addBook";
    }

    @PostMapping("addBook")
    public String addBook(
            @RequestParam(required = false, defaultValue = "") String ISBN,
            @RequestParam(required = false, defaultValue = "") Integer category_id,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String author,
            @RequestParam(required = false, defaultValue = "0") int mrp,
            @RequestParam(required = false, defaultValue = "0") int security,
            @RequestParam(required = false, defaultValue = "0") int rent,
            @RequestParam(required = false, defaultValue = "0") Integer qty,
            @RequestParam(required = false) MultipartFile img,
            @RequestParam(required = false, defaultValue = "") String short_desc,
            @RequestParam(required = false, defaultValue = "") String description,
            Model model) {

        // Gọi phương thức addBook từ bookService
        boolean isAdded = bookService.addBook(ISBN, category_id, name, author, mrp, security, rent, qty, img, short_desc, description);

        // Kiểm tra kết quả và chuyển hướng
        if (isAdded) {
            model.addAttribute("message", "Sách đã được thêm thành công!");
        } else {
            model.addAttribute("message", "Lỗi! Không thể thêm sách.");
        }
        return "Admin/addBook"; // Trả về view addBook
    }

    @GetMapping("OrderManager")
    public String order(Model model ) {
        model.addAttribute("orders" , orderService.orderList());
        model.addAttribute("status" ,orderStatusService.getAllStatus());
        return "Admin/Order";
    }

    @PostMapping("OrderManager")
    public String order(Model model ,@RequestParam int id , @RequestParam int status_id ) {
        orderService.changeOrderStatus(id ,status_id);
            model.addAttribute("orders" , orderService.orderList());
            model.addAttribute("status" ,orderStatusService.getAllStatus());

        return "redirect:/Admin/OrderManager";
    }

    @GetMapping("userManager")
    public String userManager(Model model , @RequestParam(required = false , defaultValue = "-1" ) int id  ) {
        if(id == -1 ){
            model.addAttribute("users" ,userService.getAllUser());
            return "Admin/Users";
        }
        else {
            userService.deleteUser(id);
            model.addAttribute("users" ,userService.getAllUser());
            return "Admin/Users";
        }
    }

    @GetMapping("ContactManager")
    public String order(Model model ,@RequestParam(required = false ,defaultValue =  "-1") int id  ) {
        if(id == -1) {
            model.addAttribute("contacts", contactService.getAllContact());
            return "Admin/Contacts";
        }
        else {
            contactService.deleteContact(id);
            return "redirect:/Admin/ContactManager";
        }
    }

}
