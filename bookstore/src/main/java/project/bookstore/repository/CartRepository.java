package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bookstore.entity.Book;
import project.bookstore.entity.Cart;

public interface CartRepository extends JpaRepository<Book, Integer> {
}
