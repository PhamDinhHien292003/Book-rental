package com.example.book_rental.Service;


import com.example.book_rental.DTO.BookDTO;
import com.example.book_rental.DTO.OrderDTO;
import com.example.book_rental.DTO.myOrderDTO;
import com.example.book_rental.Entity.Books;
import com.example.book_rental.Entity.Order_details;
import com.example.book_rental.Entity.Orders;
import com.example.book_rental.Entity.Users;
import com.example.book_rental.Repository.BooksRepository;
import com.example.book_rental.Repository.OrderDetailRepository;
import com.example.book_rental.Repository.OrderRepository;
import com.example.book_rental.Service.Imp.OrderImp;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderImp {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    BooksRepository booksRepository;





    @Override
    public int addOrder(Users idUser, String adr, String adr2, int pin, String payment, int total, String paymentStatus, int orderStatus, Date date, int duration , BookDTO book  ) {

        Orders order = new Orders(idUser, adr, adr2, pin , payment , total , paymentStatus , orderStatus , date , duration);
        try{
            Orders saveOrder = orderRepository.save(order);
            addOrderDetails(saveOrder.getId() , book.getId() , total  , duration);
            reduceQty(book.getId().intValue());
            return saveOrder.getId();
        }catch (Exception ex){
            ex.printStackTrace();
            return -1 ;
        }
    }



    @Override
    public List<Object[]> allListOfUser(String gmail) {
        return orderRepository.findOrdersByEmail(gmail);
    }

    @Override
    public boolean deleteOrder(int id) {
        try {
            orderRepository.deleteById(id);
            return true ;
        }catch (Exception ex){
            ex.printStackTrace();
            return false ;
        }
    }

    @Override
    public List<Object[]> orderList() {
        return orderRepository.getAllOrder();
    }

    public OrderDTO toDTO(Orders order) {
        return new OrderDTO(
                order.getId(),                // ID đơn hàng
                order.getAddress(),           // Địa chỉ
                order.getAddress2(),          // Địa chỉ 2
                order.getPin(),               // Mã pin
                order.getPaymentMethod(),     // Phương thức thanh toán
                order.getTotal(),             // Tổng tiền
                order.getPaymentStatus(),      // Trạng thái thanh toán
                order.getOrderStatus(),       // Trạng thái đơn hàng
                order.getDate(),              // Ngày đặt hàng
                order.getDuration(),          // Thời gian
                order.getUser() != null ? order.getUser().getId() : 0 // ID người dùng, mặc định là 0 nếu người dùng null
        );
    }

    public void addOrderDetails(int id ,Long  bookid  ,int price , int duration  ){
        Order_details orderDetails = new Order_details((long)id , bookid ,  price , duration) ;
        orderDetailRepository.save(orderDetails);
    }

    public void reduceQty(int id){
        Books books = booksRepository.findByid(id);
        int qty = books.getQty();
        books.setQty(books.getQty()-1);
        booksRepository.save(books);
    }


    public boolean changeOrderStatus(int id , int idStatus ){
        Optional<Orders> order = orderRepository.findById(id);
        if(order.isPresent()){
            order.get().setOrderStatus(idStatus);
            orderRepository.save(order.get());
            return true;
        }
        return false ;
    }

}
