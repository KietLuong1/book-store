package project.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.bookstore.entity.Cart;
import project.bookstore.entity.Category;
import project.bookstore.entity.CheckoutInformation;
import project.bookstore.entity.Order;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.service.CartService;
import project.bookstore.service.CategoryService;
import project.bookstore.service.CheckoutService;
import project.bookstore.service.UserService;

import java.util.List;

@Controller
public class CheckOutController {
    @Autowired private CartService cartService;
    @Autowired private CheckoutService checkoutService;
    @Autowired private UserService userService;
    @Autowired private CategoryService categoryService;

    @GetMapping("/shop-checkout")
    public String getShopCheckout(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        // View Cart
        User user = getAuthenticatedUser(userDetails);
        List<Cart> carts = cartService.listCart(user);

        float estimatedTotal = 0.0F;

        for (Cart cart : carts) {
            estimatedTotal += cart.getSubtotal();
        }

        // Get Shopping Cart Total Number Of Items
        int numberOfCartItems = cartService.getTotalNumberOfItems(userDetails.getUser());
        model.addAttribute("numberOfCartItems", numberOfCartItems);

        model.addAttribute("carts", carts);
        model.addAttribute("estimatedTotal", estimatedTotal);

        CheckoutInformation checkoutInfo = checkoutService.prepareCheckout(carts);
        model.addAttribute("cartItems", carts);
        model.addAttribute("newOrder", new Order());

        return "Client/shop-checkout";
    }

    private User getAuthenticatedUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String email = userDetails.getUsername();
        return userService.getUserByEmail(email);
    }
}
