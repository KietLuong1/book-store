package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.service.BookService;
import project.bookstore.service.CartService;
import project.bookstore.service.CategoryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.bookstore.entity.Book;
import project.bookstore.entity.Cart;
import project.bookstore.entity.Category;
import project.bookstore.entity.Order;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.service.*;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @GetMapping("/shop-cart")
    public String getAllCartItems(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);
//        // Get All Cart Items
        Set<Integer> listBooksId = cartService.getAllCartItems();
        List<Book> listCartItems = new ArrayList<>();
        for (int i : listBooksId) {
            listCartItems.add(bookService.getBookById(i));
        }

        model.addAttribute("listCartItems", listCartItems);
      }

    @GetMapping("/shop-cart/{userId}")
    public String showCart(@PathVariable Long userId, Model model) {
        model.addAttribute("cart", cartService.findByUser(userId));
        model.addAttribute("userId", userId);
        return "Client/shop-cart";
    }

    //    @RequestMapping("/shop-cart/save/{id}")
//    public String addNewItems(@PathVariable("id") Integer book_id, Model model) {

    @PostMapping("/shop-cart/save/{bookId}/{userId}")
    public String addToCart(@PathVariable("bookId") Integer bookId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getUser().getId();
        cartService.addBookToCart(bookId, userId);
        return "redirect:/shop-cart/" + userId;
    }

//    @PostMapping("/shop-cart/save/{bookId}/{userId}")
//    public String addToCart(@PathVariable("bookId") Integer bookId, @PathVariable("userId") Long userId) {
//        cartService.addBookToCart(bookId, userId);
//        return "redirect:/shop-cart" + userId;
//    }

//    @PostMapping("/shop-cart/delete")
//    public String removeFromCart(@RequestParam int cartId, @RequestParam Long userId) {
//        cartService.removeProductFromCart(cartId, userId);
//        return "redirect:/shop-cart" + userId;
//    }

    //
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
//
//    @RequestMapping("/shop-cart/save/{id}")
//    public String addNewItems(@PathVariable("id") Integer book_id, Model model) {
//        cartService.addNewItems(book_id);
//
//        Integer bookQuantity = cartService.getQuantityOfItem(book_id);
//        model.addAttribute("bookQuantity", bookQuantity);
//
//        return "redirect:/shop-cart";
//    }
//
//    @RequestMapping("/shop-cart/delete/{id}")
//    public String deleteCartItem(@PathVariable("id") Integer id) {
//        cartService.deleteItemsById(id);
//        return "redirect:/shop-cart";
//    }

}
