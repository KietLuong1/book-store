package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.bookstore.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Long countById(Integer id);

}
