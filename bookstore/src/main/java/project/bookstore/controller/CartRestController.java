package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.exception.UserNotFoundException;
import project.bookstore.service.BookService;
import project.bookstore.service.CartService;
import project.bookstore.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.bookstore.entity.Category;
import project.bookstore.service.UserService;

import java.util.List;

@RestController
public class CartRestController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/shop-cart/add/{bookId}/{quantity}")
    public String addBookToCart(@PathVariable("bookId") Integer bookId, @PathVariable("quantity") Integer quantity, @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            User user = getAuthenticatedUser(userDetails);
            Integer updatedQuantity = cartService.addBookToCart(bookId, quantity, user);

            return updatedQuantity + "item(s) of this book were added to your shopping cart";
        } catch (UserNotFoundException ex) {
            return "You must login to add this book to cart.";
        }
    }

    private User getAuthenticatedUser(@AuthenticationPrincipal CustomUserDetails userDetails) throws UserNotFoundException {
        String email = userDetails.getUsername();

        if (email == null) {
            throw new UserNotFoundException("No authenticated user");
        }
        return userService.getUserByEmail(email);
    }

    @PostMapping("/shop-cart/update/{bookId}/{quantity}")
    public String updateQuantity(@PathVariable("bookId") Integer bookId, @PathVariable("quantity") Integer quantity, @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            User user = getAuthenticatedUser(userDetails);
            float subTotal = cartService.updateQuantity(bookId, quantity, user);

            return String.valueOf(subTotal);
        } catch (UserNotFoundException ex) {
            return "You must login to change quantity of books.";
        }
    }

}
