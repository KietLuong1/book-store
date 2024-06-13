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
import org.springframework.web.bind.annotation.PathVariable;
import project.bookstore.entity.*;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.exception.NewsNotFoundException;
import project.bookstore.service.*;

import java.util.Arrays;
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
    @Autowired
    private NewsService newsService;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // Get All Categories Name from DB to Homepage
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        // Get All Book from DB to Homepage
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        List<Slider> sliders = sliderService.getSelectedSlider();
        model.addAttribute("sliders", sliders);

        //notification if user log in or not
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication == null || authentication instanceof AnonymousAuthenticationToken)){
            model.addAttribute("message", "Log in successfully");

            // Get Shopping Cart Total Number Of Items
            int numberOfCartItems = cartService.getTotalNumberOfItems(getAuthenticatedUser(userDetails));
            model.addAttribute("numberOfCartItems", numberOfCartItems);
        }

        return "Client/index";
    }

    private User getAuthenticatedUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String email = userDetails.getUsername();
        return userService.getUserByEmail(email);
    }

    @GetMapping("/about-us")
    public String getAboutUs(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/about-us";
    }

    @GetMapping("/blog-detail/{id}")
    public String getBlogDetail(@PathVariable("id") Integer id, Model model) throws NewsNotFoundException {
        // Get All Catagories Name
        News news = newsService.get(id);
        if (news != null) {
            List<String> paragraphs = Arrays.asList(news.getDescription_news().split("\n"));
            model.addAttribute("news", news);
            model.addAttribute("paragraphs", paragraphs);
        }
        model.addAttribute("news", news);

        return "Client/blog-detail";
    }

    @GetMapping("/blog-list-sidebar")
    public String getBlogListSidebar(Model model) {
        // Get All Catagories Name
        List<News> listNews = newsService.listAll();
        model.addAttribute("listNews", listNews);

        return "Client/blog-list-sidebar";
    }

    @GetMapping("/books-detail")
    public String getBooksDetail(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-detail";
    }

    @GetMapping("/books-grid-view")
    public String getBooksGridView(Model model) {
        // Get All Book
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-grid-view";
    }

    @GetMapping("/books-grid-view-sidebar")
    public String getBooksGridViewSidebar(Model model) {
        // Get All Book
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-grid-view-sidebar";
    }

    @GetMapping("/books-list")
    public String getBooksList(Model model) {
        // Get All Book
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-list";
    }

    @GetMapping("/books-list-view-sidebar")
    public String getBooksListViewSidebar(Model model) {
        // Get All Book
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-list-view-sidebar";
    }

    @GetMapping("/coming-soon")
    public String getComingSoon(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/coming-soon";
    }

    @GetMapping("/contact-us")
    public String getContactUs(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/contact-us";
    }

    @GetMapping("/404")
    public String get404(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "/Client/error-404";
    }

    @GetMapping("/faq")
    public String getFAQ(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/faq";
    }

    @GetMapping("/pricing")
    public String getPricing(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/pricing";
    }

    @GetMapping("/privacy-policy")
    public String getPrivacyPolicy(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/privacy-policy";
    }

    @GetMapping("/services")
    public String getServices(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/services";
    }

//    @GetMapping("/shop-checkout")
//    public String getShopCheckout(Model model) {
//        // Get All Catagories Name
//        List<Category> listCategoriesName = categoryService.listAll();
//        model.addAttribute("listCategoriesName", listCategoriesName);
//
//        return "Client/shop-checkout";
//    }

    @GetMapping("/shop-login")
    public String getShopLogin() {
        return "Client/shop-login";
    }

    @GetMapping("/shop-registration")
    public String getShopRegistration() {
        return "Client/shop-registration";
    }

    @GetMapping("/support")
    public String getSupport(Model model) {
        List<Category> listCategoriesName = categoryService.getAllCategories();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/support";
    }

    @GetMapping("/under-construction")
    public String getUnderConstruction() {
        return "Client/under-construction";
    }

}
