package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.entity.Book;
import project.bookstore.entity.Publisher;
import project.bookstore.repository.PublisherRepository;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public List<Publisher> getAllPublisher() {
        return (List<Publisher>) publisherRepository.findAll();
    }

    public Publisher getPublisherById(int id) {
        return publisherRepository.findById(id).get();
    }

    public void deleteById(int id) {
        publisherRepository.deleteById(id);
    }
}
