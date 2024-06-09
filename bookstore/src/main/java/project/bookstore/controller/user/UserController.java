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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Address;
import project.bookstore.entity.Book;
import project.bookstore.entity.Category;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.exception.CategoryNotFoundException;
import project.bookstore.service.CategoryService;
import project.bookstore.service.CloudinaryService;
import project.bookstore.service.UserService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CloudinaryService cloudinaryService;

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

        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/my-profile";
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
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/order-history";
    }

    @GetMapping("/shop-checkout")
    public String getShopCheckout(Model model) {
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/shop-checkout";
    }

    // User Management
    @GetMapping("/user-management")
    public String getAllUsers(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "Admin/user-management";
    }

    @RequestMapping("/user-management/user-detail/{id}")
    public String getUserDetail(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "Admin/user-detail";
    }

    @RequestMapping("/user-management/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user-management";
    }

    @RequestMapping("/user-management/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "Admin/user-edit";
    }

    @PostMapping("/user-management/update")
    public String updateUser(@ModelAttribute(name = "user") User user) {
        try {
            User updateUser = userService.save(user);

            userService.save(updateUser);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user-edit";
    }

}
