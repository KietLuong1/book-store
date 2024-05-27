package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.Book;
import project.bookstore.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
