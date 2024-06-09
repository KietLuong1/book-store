package project.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin-dashboard")
    public String getDashboard() {
        return "Admin/admin-dashboard";
    }

    @GetMapping("/admin-profile")
    public String getProfile() {
        return "Admin/admin-profile";
    }

    @GetMapping("user-edit")
    public String editUserProfile() {
        return "Admin/user-edit";
    }

    @GetMapping("user-detail")
    public String addUser() {
        return "Admin/user-detail";
    }

    @GetMapping("account-setting")
    public String getAccontSetting() {
        return "Admin/account-setting";
    }

    @GetMapping("privacy-setting")
    public String getPrivacySetting() {
        return "Admin/privacy-setting";
    }
}
