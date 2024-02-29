package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.Category;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Long countById(Integer id);

}
