package project.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.bookstore.entity.Author;
import project.bookstore.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorReposiory;

    public void save(Author author) {
        authorReposiory.save(author);
    }

    public List<Author> getAllAuthor() {
        return authorReposiory.findAll();
    }

    public Author getAuthorById(int id) {
        return authorReposiory.findById(id).get();
    }

    public void deleteById(int id) {
        authorReposiory.deleteById(id);
    }

}
