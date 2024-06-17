package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.entity.Cart;
import project.bookstore.entity.CheckoutInformation;
import project.bookstore.repository.CheckoutRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckoutService {
    @Autowired
    private CheckoutRepository checkoutRepository;

    public void save(CheckoutInformation checkoutInformation) {
        checkoutRepository.save(checkoutInformation);
    }
}
