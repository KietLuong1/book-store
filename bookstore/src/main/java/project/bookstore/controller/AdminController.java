package project.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    
    @GetMapping("admin-dashboard")
    public String getDashboard() {
        return "Admin/admin-dashboard";
    }

//    @GetMapping("admin-category")
//    public String getCategory() {
//        return "Admin/admin-category";
//    }

    @GetMapping("admin-author")
    public String getAuthor() {
        return "Admin/admin-author";
    }

    @GetMapping("admin-books")
    public String getBooks() {
        return "Admin/admin-books";
    }

    @GetMapping("profile")
    public String getProfile() {
        return "Admin/profile";
    }

    @GetMapping("profile-edit")
    public String editProfile() {
        return "Admin/profile-edit";
    }

    @GetMapping("add-user")
    public String addUser() {
        return "Admin/add-user";
    }

    @GetMapping("user-list")
    public String getUserList() {
        return "Admin/user-list";
    }

    @GetMapping("account-setting")
    public String getAccontSetting() {
        return "Admin/account-setting";
    }

    @GetMapping("privacy-setting")
    public String getPrivacySetting() {
        return "Admin/privacy-setting";
    }

    @GetMapping("admin-add-author")
    public String addAuthor() {
        return "Admin/admin-add-author";
    }

    @GetMapping("admin-add-book")
    public String addBook() {
        return "Admin/admin-add-book";
    }

}
