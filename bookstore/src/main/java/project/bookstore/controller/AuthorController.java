package project.bookstore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Author;
import project.bookstore.exception.AuthorNotFoundException;
import project.bookstore.exception.CategoryNotFoundException;
import project.bookstore.service.AuthorService;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService service;

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
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        author.setProfileImage(fileName);

        Author savedAuthor = service.save(author);

        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path staticPath = Paths.get("src/main/resources/static/Admin");
        System.out.println(staticPath);
        System.out.println(currentPath);

        String uploadDir = currentPath.resolve(staticPath) +"/images/author-images/"+ savedAuthor.getId();

        System.out.println(uploadDir);

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }

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
        } catch (CategoryNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin-author";
    }
}
