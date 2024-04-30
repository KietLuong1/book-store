package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bookstore.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
