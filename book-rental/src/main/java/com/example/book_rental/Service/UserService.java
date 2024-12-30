package com.example.book_rental.Service;
import com.example.book_rental.DTO.UserDTO;
import com.example.book_rental.Entity.Users;
import com.example.book_rental.Repository.UserRepository;
import com.example.book_rental.Service.Imp.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UserImp {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean checkLogin(String email, String password) {
        Optional op = userRepository.findByEmail(email);
        if (op.isPresent()) {
            Users user = (Users) op.get();
            return  passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public UserDTO getUserInfomation(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return new UserDTO(user.get().getId() , user.get().getName(),user.get().getEmail() , user.get().getTel() , user.get().getPassword());
        }
        else {
            return null ;
        }
    }

    @Override
    public boolean editProfile(String name, String email, String phone, String password) {
        try{
            Optional<Users> user = userRepository.findByEmail(email);
            if(user.isPresent()) {
                user.get().setName(name);
                user.get().setEmail(email);
                user.get().setTel(phone);
                String encodedPassword = passwordEncoder.encode(password);
                user.get().setPassword(encodedPassword);
                userRepository.save(user.get());
                return true;
            }
            return false;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public String signUp(String name, String email, String phone, String password) {
        String msg = "";
        if(userRepository.findByEmail(email).isPresent()) {
            msg += "Email đã tồn tại \n" ;
            return msg ;
        }
        if(name.trim().length() <= 5){
            msg = "Tên phải dài hơn 5 kí tự \n";
            return msg ;

        }
        if(password.trim().length() <= 5){
            msg = "Password phải lớn hơn 5 kí tự \n";
            return msg ;
        }
        LocalDate date = LocalDate.now();
        Date dateNow = java.sql.Date.valueOf(date);

        String encodedPassword = passwordEncoder.encode(password);

        Users users = new Users(name ,email, phone ,dateNow,encodedPassword );

        userRepository.save(users);
        return "";
    }


}
