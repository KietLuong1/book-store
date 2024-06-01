package project.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
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
import project.bookstore.entity.Book;
import project.bookstore.entity.Category;
import project.bookstore.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/shop-cart")
    public String getAllCartItems(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);
        // Get All Cart Items
        Set<Integer> listBooksId = cartService.getAllCartItems();
        List<Book> listCartItems = new ArrayList<>();
        for (int i : listBooksId) {
            listCartItems.add(bookService.getBookById(i));
        }

        model.addAttribute("listCartItems", listCartItems);
        return "Client/shop-cart";
    }

    @GetMapping("/shop-cart/{userId}")
    public String showCart(@PathVariable Long userId, Model model) {
//        model.addAttribute("cart", cartService.findByUser(userId));
//        model.addAttribute("userId", userId);
        return "Client/shop-cart";
    }

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

//    @PostMapping("/shop-cart/delete")
//    public String removeFromCart(@RequestParam int cartId, @RequestParam Long userId) {
//        cartService.removeProductFromCart(cartId, userId);
//        return "redirect:/shop-cart" + userId;
//    }

//    @GetMapping("/shop-cart")
//    public String getAllCartItems(Model model) {
//        // Get All Catagories Name
//        List<Category> listCategoriesName = categoryService.listAll();
//        model.addAttribute("listCategoriesName", listCategoriesName);
//        // Get All Cart Items
//        Set<Integer> listBooksId = cartService.getAllCartItems();
//        List<Book> listCartItems = new ArrayList<>();
//        for (int i : listBooksId) {
//            listCartItems.add(bookService.getBookById(i));
//        }
//
//        model.addAttribute("listCartItems", listCartItems);
//
//        return "Client/shop-cart";
//    }

//    @RequestMapping("/shop-cart/delete/{id}")
//    public String deleteCartItem(@PathVariable("id") Integer id) {
//        cartService.deleteItemsById(id);
//        return "redirect:/shop-cart";
//    }

}
