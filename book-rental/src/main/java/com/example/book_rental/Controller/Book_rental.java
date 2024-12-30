package com.example.book_rental.Controller;

import com.example.book_rental.DTO.BookDTO;
import com.example.book_rental.Entity.Users;
import com.example.book_rental.Repository.UserRepository;
import com.example.book_rental.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/index")
public class Book_rental {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactService contactService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("list_books", bookService.getAllBook());
        return "index";
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("category" , categoryService.getAllCategory());
        model.addAttribute("list_books", bookService.getAllBook());
        return "Books";
    }

    @GetMapping("/books/{id}")
    public String books(Model model , @PathVariable int id) {
        model.addAttribute("category" , categoryService.getAllCategory());
        model.addAttribute("list_books" , bookService.getAllBookByCategory(categoryService.getAllbookbycate(id).getId()));
        return "Books";
    }

    @GetMapping("/book-details/{id}")
    public String bookDetails(Model model , @PathVariable int id) {
        model.addAttribute("details" , bookService.getBookById(id));
        return "book-details";
    }


    @GetMapping("/login-page")
    public  String login(HttpSession session , @RequestParam(value = "back", required = false) String redirect ){
        if(session.getAttribute("user") != null){
            return "index";
        }
        else{
            if (redirect != null && !redirect.isEmpty()) {
                session.setAttribute("back" , redirect);
            }
            return "login";
        }
    }

    @PostMapping("/login")
    public  String login(HttpSession session , @RequestParam String email , @RequestParam String password ,Model model ){
        if(userService.checkLogin(email,password)){
            session.setAttribute("user" , email);
            if(session.getAttribute("back") != null){
                String back = (String) session.getAttribute("back");
                session.removeAttribute("back");
                return "redirect:/index/"+back;
            }
            return "redirect:/index";
        }else{
            return "redirect:/index/login-page?error=Invalid email or password";
        }
    }

    @GetMapping("/log-out")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index";
    }


    @GetMapping("/check-out")
    public String checkOut(HttpSession session , @RequestParam(value = "bookId" , required = false , defaultValue = "0") int id , @RequestParam(value = "duration" , required = false , defaultValue = "0") int duration , Model model ){
        if(session.getAttribute("user") != null){
            if(duration == 0 ) {
                Object o = session.getAttribute("info");
                if (o instanceof Object[]) {
                    Object[] ob = (Object[]) o;
                    id = (int) ob[0];
                    duration = (int) ob[1];
                    session.removeAttribute("info");
                }
            }
            session.setAttribute("book", bookService.getBookById(id));
            session.setAttribute("duration", duration);
            return "Checkout";
        }
        else {
            Object[] ob = new Object[]{id , duration};
            session.setAttribute("info" , ob);
            return "redirect:/index/login-page?back=check-out";
        }
    }


    @PostMapping("payment")
    public String payment(HttpSession session , @RequestParam String address , @RequestParam String address2 , @RequestParam String pin , Model model ){
        var user = session.getAttribute("user");
        Optional<Users> idUser = userRepository.findByEmail(user.toString());
        int duration = (int)session.getAttribute("duration") ;
        BookDTO book = (BookDTO) session.getAttribute("book") ;
        int idorder = orderService.addOrder(idUser.get() , address , address2 , Integer.parseInt(pin) ,
                "COD" , duration *book.getRent()  ,"Thành công" , 1, Date.valueOf(LocalDate.now()) , duration ,
                book);
        model.addAttribute("idorder", idorder);
        return "message";
    }


    @GetMapping("my-order")
    public String myOrder( HttpSession session , Model model){
        if(session.getAttribute("user") != null){
            String gmail = (String) session.getAttribute("user");
            model.addAttribute("order" , orderService.allListOfUser(gmail));
            return "myOrder";
        }
        else {
            return "redirect:/index/login-page?back=my-order";
        }
    }

    @GetMapping("cancel-order")
    public String cancel(@RequestParam int id ){
        if(orderService.deleteOrder(id)){
            return "redirect:/index/my-order";
        }
        else{
            return "login";
        }
    }

    @GetMapping("introduction")
    public String intro(){
            return "introduction";
    }

    @GetMapping("clause")
    public String clause(){
        return "clause";
    }

    @GetMapping("contact")
    public String contact(HttpSession session , Model model){
        if(session.getAttribute("user") != null){
            model.addAttribute("user" , userService.getUserInfomation(session.getAttribute("user").toString()));
            return "contact";
        }
        else{
            return "redirect:/index/login-page?back=contact";
        }
    }

    @PostMapping("handleContact")
    public String contact(@RequestParam String name , @RequestParam String email , @RequestParam String mobile  , @RequestParam String message , Model model , HttpSession session ){
        if(contactService.addContact(name , email , mobile , message)){
            model.addAttribute("user" , userService.getUserInfomation(session.getAttribute("user").toString()));
            model.addAttribute("succesMsg" , "ok");
        }
        else{
            model.addAttribute("danger" , "error");
        }
        return "contact" ;
    }

    @GetMapping("profile")
    public String profile(Model model , HttpSession session){
        model.addAttribute("user" , userService.getUserInfomation(session.getAttribute("user").toString()));
        return "Profile";
    }

    @PostMapping("editProfile")
    public String editPrf( HttpSession session ,Model model , @RequestParam String name , @RequestParam String email , @RequestParam String mobile , @RequestParam String password ){
        if(userService.editProfile(name , email , mobile , password)){
            model.addAttribute("succesMsg" , "ok");
            model.addAttribute("user" , userService.getUserInfomation(session.getAttribute("user").toString()));
            return "Profile";
        }
        else {
            model.addAttribute("danger" , "error");
            model.addAttribute("user" , userService.getUserInfomation(session.getAttribute("user").toString()));
            return "Profile";
        }
    }


    @GetMapping("sign-up")
    public String signUpPage(){
        return "SignUp";
    }


    @PostMapping("signUp")
    public String signUp(@RequestParam String name , @RequestParam String email , @RequestParam String mobile , @RequestParam String password ,Model model ){
        String msg = userService.signUp(name , email , mobile , password);
        if(!msg.isEmpty()) {
            model.addAttribute("error", msg);
            model.addAttribute("lst", new Object[]{name, email, mobile, password});
            return "SignUp";

        }
        else {
            return "login";
        }
    }
}
