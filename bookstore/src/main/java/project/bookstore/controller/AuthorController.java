package project.bookstore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Author;
import project.bookstore.exception.AuthorNotFoundException;
import project.bookstore.service.AuthorService;
import project.bookstore.service.CloudinaryService;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService service;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/admin-author")
    public String showBookCategory(Model model) {
        List<Author> authors = service.listAll();
        model.addAttribute("authors", authors);

        return "Admin/admin-author";
    }

    @GetMapping("/admin-add-author")
    public String showNewForm(Model model) {
        model.addAttribute("author", new Author());

        return "Admin/admin-add-author";
    }

    @PostMapping("admin-add-author/save")
    public String saveAuthor(@ModelAttribute(name = "author") Author author, RedirectAttributes ra,
                             @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        Author savedAuthor = service.save(author);

        savedAuthor.setProfileImageURL(cloudinaryService.uploadFile(multipartFile, "Admin/authors/" + savedAuthor.getId()));
        service.save(savedAuthor);

        ra.addFlashAttribute("message", "Author has been saved successfully");

        return "redirect:/admin-author";
    }

    @GetMapping("/admin-author/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Author author = service.get(id);
            model.addAttribute("author", author);
            model.addAttribute("pageTitle", "Edit Author (ID: " + id + ")");

            return "Admin/admin-add-author";
        } catch (AuthorNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin-author";
        }
    }

    @GetMapping("/admin-author/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The author ID " + id + " has been deleted");
        } catch (AuthorNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin-author";
    }
}
