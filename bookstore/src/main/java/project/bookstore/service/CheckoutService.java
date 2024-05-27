package project.bookstore.service;

import org.springframework.stereotype.Service;
import project.bookstore.entity.Cart;
import project.bookstore.entity.Checkout;

import java.util.List;

@Service
public class CheckoutService {

    public Checkout prepareCheckout (List<Cart> cartItems) {
        Checkout checkoutInfo = new Checkout();

        float productCost = calculateProductCost(cartItems);

        return checkoutInfo;
    }

    private float calculateProductCost(List<Cart> cartItems) {
        float cost = 0.0f;

        for (Cart item: cartItems) {

        }

        return 0;
    }
}
