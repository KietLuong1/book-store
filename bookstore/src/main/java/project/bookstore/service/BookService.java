package project.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.bookstore.entity.Book;
import project.bookstore.entity.Category;
import project.bookstore.repository.BookRepository;
import project.bookstore.repository.CategoryRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookReposiory;

    @Autowired
    private CategoryRepository categoryRepository;

    public Book save(Book book) {
        return bookReposiory.save(book);
    }

    public List<Book> getAllBook() {
        return (List<Book>) bookReposiory.findAll();
    }

    public Book getBookById(int id) {
        return bookReposiory.findById(id).get();
    }

    public void deleteById(int id) {
        bookReposiory.deleteById(id);
    }

}
