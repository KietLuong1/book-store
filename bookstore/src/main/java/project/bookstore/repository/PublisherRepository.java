package project.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.bookstore.entity.Publisher;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Integer> {

}
