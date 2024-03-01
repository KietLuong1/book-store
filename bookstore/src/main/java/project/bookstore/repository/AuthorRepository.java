package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.bookstore.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

   public Long countById(Integer id);
}
