package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.bookstore.entity.Book;
import project.bookstore.entity.Cart;
import project.bookstore.entity.Category;
import project.bookstore.entity.Slider;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.service.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;

    @Autowired
    CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private SliderService sliderService;

    // Get all needed information from DB and show to all pages
    @ModelAttribute
    public void showInformation(Model model){
        //notification if user log in or not
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication == null || authentication instanceof AnonymousAuthenticationToken)){
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Get Shopping Cart Total Number Of Items
            int numberOfCartItems = cartService.getTotalNumberOfItems(userDetails.getUser());
            model.addAttribute("numberOfCartItems", numberOfCartItems);
        }

        // Get All Categories Name from DB to Homepage
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        // Get All Books from DB to Homepage
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        // Get All Sliders from DB to Homepage
        List<Slider> sliders = sliderService.getSelectedSlider();
        model.addAttribute("sliders", sliders);
    }

    @GetMapping("/")
    public String home() {
        return "Client/index";
    }

    @GetMapping("/about-us")
    public String getAboutUs() {
        return "Client/about-us";
    }

    @GetMapping("/blog-detail")
    public String getBlogDetail() {
        return "Client/blog-detail";
    }

    @GetMapping("/blog-list-sidebar")
    public String getBlogListSidebar() {
        return "Client/blog-list-sidebar";
    }

    @GetMapping("/books-grid-view")
    public String getBooksGridView() {
        return "Client/books-grid-view";
    }

    @GetMapping("/books-grid-view-sidebar")
    public String getBooksGridViewSidebar() {
        return "Client/books-grid-view-sidebar";
    }

    @GetMapping("/books-list-view-sidebar")
    public String getBooksListViewSidebar() {
        return "Client/books-list-view-sidebar";
    }

    @GetMapping("/coming-soon")
    public String getComingSoon() {
        return "Client/coming-soon";
    }

    @GetMapping("/contact-us")
    public String getContactUs() {
        return "Client/contact-us";
    }

    @GetMapping("/404")
    public String get404() {
        return "/Client/error-404";
    }

    @GetMapping("/faq")
    public String getFAQ() {
        return "Client/faq";
    }

    @GetMapping("/pricing")
    public String getPricing() {
        return "Client/pricing";
    }

    @GetMapping("/privacy-policy")
    public String getPrivacyPolicy() {
        return "Client/privacy-policy";
    }

    @GetMapping("/services")
    public String getServices() {
        return "Client/services";
    }

    @GetMapping("/shop-login")
    public String getShopLogin() {
        return "Client/shop-login";
    }

    @GetMapping("/support")
    public String getSupport() {
        return "Client/support";
    }

    @GetMapping("/under-construction")
    public String getUnderConstruction() {
        return "Client/under-construction";
    }

}
