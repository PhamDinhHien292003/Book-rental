package com.example.book_rental.Service;

import com.example.book_rental.Entity.Admin;
import com.example.book_rental.Repository.AdminRepository;
import com.example.book_rental.Service.Imp.AdminImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminService implements AdminImp {
    @Autowired
    private AdminRepository adminRepository;


    @Override
    public boolean checkLogin(String email, String password) {
        Optional<Admin> admin = Optional.ofNullable(adminRepository.findByEmail(email));
        if(admin.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(password, admin.get().getPassword());
        }
        return false;
    }
}
