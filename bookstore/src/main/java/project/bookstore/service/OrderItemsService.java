package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.entity.Order;
import project.bookstore.entity.OrderItems;
import project.bookstore.repository.BookRepository;
import project.bookstore.repository.OrderItemsRepository;
import project.bookstore.repository.OrderRepository;
import project.bookstore.repository.UserRepository;

@Service
public class OrderItemsService {

    @Autowired
    private BookRepository bookReposiory;
    private OrderRepository orderRepository;
    private OrderItemsRepository orderItemsRepository;
    private UserRepository userRepository;

    public void save(OrderItems order) {
        orderItemsRepository.save(order);
    }



    public void deleteById(int id) {
        orderItemsRepository.deleteById(id);
    }
}
