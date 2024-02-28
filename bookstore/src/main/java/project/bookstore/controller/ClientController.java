package project.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    @GetMapping("")
    public String home() {
        return "Client/index";
    }

    @GetMapping("about-us")
    public String getAboutUs() {
        return "Client/about-us";
    }

    @GetMapping("blog-detail")
    public String getBlogDetail() {
        return "Client/blog-detail";
    }

    @GetMapping("blog-list-sidebar")
    public String getBlogListSidebar() {
        return "Client/blog-list-sidebar";
    }

    @GetMapping("books-detail")
    public String getBooksDetail() {
        return "Client/books-detail";
    }

    @GetMapping("books-grid-view")
    public String getBooksGridView() {
        return "Client/books-grid-view";
    }
    @GetMapping("books-grid-view-sidebar")
    public String getBooksGridViewSidebar() {
        return "Client/books-grid-view-sidebar";
    }

    @GetMapping("books-list")
    public String getBooksList() {
        return "Client/books-list";
    }

    @GetMapping("books-list-view-sidebar")
    public String getBooksListViewSidebar() {
        return "Client/books-list-view-sidebar";
    }

    @GetMapping("coming-soon")
    public String getComingSoon() {
        return "Client/coming-soon";
    }

    @GetMapping("contact-us")
    public String getContactUs() {
        return "Client/contact-us";
    }

    @GetMapping("404")
    public String get404() {
        return "Client/error-404";
    }

    @GetMapping("faq")
    public String getFAQ() {
        return "Client/faq";
    }

    @GetMapping("my-profile")
    public String getMyProfile() {
        return "Client/my-profile";
    }

    @GetMapping("order-history")
    public String getOrderHistory() {
        return "Client/order-history";
    }

    @GetMapping("pricing")
    public String getPricing() {
        return "Client/pricing";
    }

    @GetMapping("privacy-policy")
    public String getPrivacyPolicy() {
        return "Client/privacy-policy";
    }

    @GetMapping("services")
    public String getServices() {
        return "Client/services";
    }

    @GetMapping("shop-cart")
    public String getShopCart() {
        return "Client/shop-cart";
    }

    @GetMapping("shop-checkout")
    public String getShopCheckout() {
        return "Client/shop-checkout";
    }

    @GetMapping("shop-login")
    public String getShopLogin() {
        return "Client/shop-login";
    }

    @GetMapping("shop-registration")
    public String getShopRegistration() {
        return "Client/shop-registration";
    }

    @GetMapping("support")
    public String getSupport() {
        return "Client/support";
    }

    @GetMapping("under-construction")
    public String getUnderConstruction() {
        return "Client/under-construction";
    }

    @GetMapping("wishlist")
    public String getWishlist() {
        return "Client/wishlist";
    }

}
