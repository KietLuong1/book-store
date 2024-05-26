package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.entity.Book;
import project.bookstore.entity.Order;
import project.bookstore.repository.BookRepository;
import project.bookstore.repository.OrderItemsRepository;
import project.bookstore.repository.OrderRepository;
import project.bookstore.repository.UserRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private BookRepository bookReposiory;
    private OrderRepository orderRepository;
    private OrderItemsRepository orderItemsRepository;
    private UserRepository userRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }



    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }
}
