package com.example.book_rental.Controller;


import com.example.book_rental.Service.AdminService;
import com.example.book_rental.Service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Admin")
public class Admin_management {

    @Autowired
    private BookService bookService;

    @Autowired
    AdminService adminService;

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
            return "Admin/index";
        }
        model.addAttribute("message", "Invalid email or password");
        return "Admin/login";
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


}
