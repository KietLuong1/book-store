package project.bookstore.controller;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Author;
import project.bookstore.entity.News;
import project.bookstore.exception.NewsNotFoundException;
import project.bookstore.service.CloudinaryService;
import project.bookstore.service.NewsService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import org.jsoup.Jsoup;

@Controller
public class NewsController {
    @Autowired
    private NewsService service;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/admin-news")
    public String showNews(Model model) {
        List<News> listNews = service.listAll();
        model.addAttribute("listNews", listNews);

        return "Admin/admin-news";
    }

    @GetMapping("/admin-add-news")
    public String showNewsForm(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("pageTitle", "Add New Article");
        return "Admin/admin-add-news";
    }

    @PostMapping("/admin-add-news/save")
    public String saveNews(@ModelAttribute(name = "news") News news, RedirectAttributes ra,
                             @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        News savedNews = service.save(news);

        savedNews.setNewsImage(cloudinaryService.uploadFile(multipartFile, "Admin/authors/" + savedNews.getId()));
        service.save(savedNews);

        ra.addFlashAttribute("message", "News has been saved successfully");

        return "redirect:/admin-news";
    }

    @GetMapping("/admin-news/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            News news = service.get(id);
            model.addAttribute("news", news);
            model.addAttribute("pageTitle", "Edit News (ID: " + id + ")");

            return "Admin/admin-add-news";
        } catch (NewsNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin-news";
        }
    }

    @GetMapping("/admin-news/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The news ID " + id + " has been deleted");
        } catch (NewsNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin-news";
    }
}
