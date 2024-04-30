package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.bookstore.entity.Address;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.enums.Status;
import project.bookstore.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/client-login")
    public String getLogin() {
        return "/Client/shop-login";
    }

    @GetMapping("/register")
    public String getRegistration(Model model) {
        model.addAttribute("newUser", new User());
        return "Client/shop-registration";
    }

    @PostMapping("/process_register")
    public String processRegister(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        Address address = new Address();

        user.setPassword(encodedPassword);
        address.setCountry(LocaleContextHolder.getLocale().getCountry());
        user.setAddress(address);
        user.setStatus(Status.ENABLED);

        userService.save(user);

        return "redirect:/client-login";
    }

    @GetMapping("/profile")
    public String getMyProfile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        model.addAttribute("user",user);

        return "Client/my-profile";
    }

    @PostMapping("/profile/save/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        System.out.println(user.toString());
        model.addAttribute("user", user);

        userService.save(user);

        return "redirect:/profile";
    }
}
