package project.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.bookstore.entity.Book;
import project.bookstore.entity.Category;
import project.bookstore.service.BookService;
import project.bookstore.service.CategoryService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String home(Model model) {
        // Get All Categories Name from DB to Homepage
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);
        // Get All Book from DB to Homepage, Book Grid, Book List
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        return "Client/index";
    }

    public String getAboutUs(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/about-us";
    }

    @GetMapping("/blog-detail")
    public String getBlogDetail(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/blog-detail";
    }

    @GetMapping("/blog-list-sidebar")
    public String getBlogListSidebar(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/blog-list-sidebar";
    }

    @GetMapping("/books-detail")
    public String getBooksDetail(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-detail";
    }

    @GetMapping("/books-grid-view")
    public String getBooksGridView(Model model) {
        // Get All Book
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-grid-view";
    }

    @GetMapping("/books-grid-view-sidebar")
    public String getBooksGridViewSidebar(Model model) {
        // Get All Book
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-grid-view-sidebar";
    }

    @GetMapping("/books-list")
    public String getBooksList(Model model) {
        // Get All Book
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-list";
    }

    @GetMapping("/books-list-view-sidebar")
    public String getBooksListViewSidebar(Model model) {
        // Get All Book
        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);

        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/books-list-view-sidebar";
    }

    @GetMapping("/coming-soon")
    public String getComingSoon(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/coming-soon";
    }

    @GetMapping("/contact-us")
    public String getContactUs(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/contact-us";
    }

    @GetMapping("/404")
    public String get404(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "/Client/error-404";
    }

    @GetMapping("/faq")
    public String getFAQ(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/faq";
    }

    @GetMapping("/my-profile")
    public String getMyProfile(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/my-profile";
    }

    @GetMapping("/order-history")
    public String getOrderHistory(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/order-history";
    }

    @GetMapping("/pricing")
    public String getPricing(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/pricing";
    }

    @GetMapping("/privacy-policy")
    public String getPrivacyPolicy(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/privacy-policy";
    }

    @GetMapping("/services")
    public String getServices(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/services";
    }

    @GetMapping("/shop-cart")
    public String getShopCart(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/shop-cart";
    }

    @GetMapping("/shop-checkout")
    public String getShopCheckout(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/shop-checkout";
    }

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
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/support";
    }

    @GetMapping("/under-construction")
    public String getUnderConstruction() {
        return "Client/under-construction";
    }

    @GetMapping("/wishlist")
    public String getWishlist(Model model) {
        // Get All Catagories Name
        List<Category> listCategoriesName = categoryService.listAll();
        model.addAttribute("listCategoriesName", listCategoriesName);

        return "Client/wishlist";
    }

}