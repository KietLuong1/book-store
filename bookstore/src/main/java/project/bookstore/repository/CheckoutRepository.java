package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.CheckoutInformation;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutInformation, Integer> {

}
