package project.bookstore.controller.user;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println(user.toString());
        model.addAttribute("user",user);

        return "/Client/my-profile";
    }

    @PostMapping("/profile/update")
    public String updateUser(Model model, User user, @AuthenticationPrincipal CustomUserDetails loggedUser) {
        model.addAttribute("user" , loggedUser);
        loggedUser.setUser(user);
        userService.save(user);
        return "redirect:/profile";
    }
}
