package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.entity.Author;
import project.bookstore.entity.Order;
import project.bookstore.exception.OrderNotFoundException;
import project.bookstore.repository.BookRepository;
import project.bookstore.repository.OrderItemsRepository;
import project.bookstore.repository.OrderRepository;
import project.bookstore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }
    public List<Order> getAllOrders() {
        return  orderRepository.findAll();
    }
    public Order get(Integer id) throws OrderNotFoundException {
        Optional<Order> result = orderRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new OrderNotFoundException("Could not find any author with ID " + id);
    }

    public void delete(Integer id) throws OrderNotFoundException {
        Long count = orderRepository.countById(id);

        if (count == null || count == 0) {
            throw new OrderNotFoundException("Could not find any author with ID " + id);
        }
        orderRepository.deleteById(id);
    }
}
