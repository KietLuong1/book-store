package project.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.bookstore.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}
