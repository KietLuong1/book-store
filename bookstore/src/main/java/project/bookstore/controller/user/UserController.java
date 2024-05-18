package project.bookstore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Address;
import project.bookstore.entity.Category;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.service.CategoryService;
import project.bookstore.service.UserService;

import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/client-login")
    public String getLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "/Client/shop-login";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegistration(Model model) {
        model.addAttribute("newUser", new User());
        return "Client/shop-registration";
    }

    @PostMapping("/process_register")
    public String processRegister(User user, RedirectAttributes ra) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        Address address = new Address();

        user.setPassword(encodedPassword);
        user.setAddress(address);

        if (userService.getUserByEmail(user.getEmail()) != null) {
            ra.addFlashAttribute("error", "User has been registered");

            return "redirect:/register";
        }
        userService.save(user);
        ra.addFlashAttribute("message", "Sign up successfully");

        return "redirect:/client-login";
    }

    @GetMapping("/profile")
    public String getMyProfile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {


        User user = userDetails.getUser();
        model.addAttribute("user", user);

        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "/Client/my-profile";
    }

    @PostMapping("/profile/update")
    public String updateUser(Model model, RedirectAttributes ra, User user, @AuthenticationPrincipal CustomUserDetails loggedUser) {
        model.addAttribute("user", loggedUser);
        loggedUser.setUser(user);
        userService.save(user);

        ra.addFlashAttribute("message", "Update information successfully");

        return "redirect:/profile";
    }

    @GetMapping("/order-history")
    public String getOrderHistory(Model model) {
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/order-history";
    }

    @GetMapping("/shop-checkout")
    public String getShopCheckout(Model model) {
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/shop-checkout";
    }

    @GetMapping("/shop-cart")
    public String getShopCart(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/shop-cart";
    }

    @GetMapping("/wishlist")
    public String getWishlist(Model model) {
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/wishlist";
    }
}
