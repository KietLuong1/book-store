package project.bookstore.service;

import org.springframework.stereotype.Service;
import project.bookstore.entity.Cart;
import project.bookstore.entity.CheckoutInformation;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckoutService {

    public CheckoutInformation prepareCheckout (List<Cart> cartItems) {
        CheckoutInformation checkoutInfo = new CheckoutInformation();

        float productTotal = calculateProductTotal(cartItems);

        checkoutInfo.setSubtotal(productTotal);
        checkoutInfo.setShippingCostTotal(calculateShippingCost(cartItems));
        checkoutInfo.setTotal(productTotal + checkoutInfo.getShippingCostTotal());
        checkoutInfo.setDeliverDays(3);
        checkoutInfo.setDeliverDate(LocalDate.now().plusDays(3));

        return checkoutInfo;
    }

//    private float calculateProductCost(List<Cart> cartItems) {
//        float cost = 0.0f;
//
//        for (Cart item: cartItems) {
//            cost += item.getBook().getPrice() * item.getQuantity();
//        }
//
//        return cost;
//    }
    private float calculateProductTotal(List<Cart> cartItems) {
        float cost = 0.0f;

        for (Cart item: cartItems) {
            cost += item.getSubtotal();
        }

        return cost;
    }

    private float calculateShippingCost(List<Cart> cartItems) {
        float cost = 0.0f;

        for (Cart item: cartItems) {
            cost += item.getShippingCost();
        }

        return cost;
    }
}
