package project.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.bookstore.entity.Author;
import project.bookstore.exception.AuthorNotFoundException;
import project.bookstore.exception.CategoryNotFoundException;
import project.bookstore.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void save(Author author) {
        authorRepository.save(author);
    }

    public List<Author> listAll() {
        return (List<Author>) authorRepository.findAll();
    }

    public Author get(Integer id) throws AuthorNotFoundException {
        Optional<Author> result = authorRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new AuthorNotFoundException("Could not find any categories with ID " + id);
    }

    public void delete(Integer id) throws CategoryNotFoundException {
        Long count = authorRepository.countById(id);
        if (count == null || count == 0) {
            throw new CategoryNotFoundException("Could not find any category with ID " + id);
        }
        authorRepository.deleteById(id);
    }

}
