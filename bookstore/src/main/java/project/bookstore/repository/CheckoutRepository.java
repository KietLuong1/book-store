package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.Checkout;
import project.bookstore.entity.Order;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

}
