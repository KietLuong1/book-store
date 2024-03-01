package project.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Author;
import project.bookstore.entity.Category;
import project.bookstore.exception.AuthorNotFoundException;
import project.bookstore.exception.CategoryNotFoundException;
import project.bookstore.repository.AuthorRepository;
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
    public String saveAuthor(Author author, RedirectAttributes ra) {
        service.save(author);
        ra.addFlashAttribute("message", "Author has been saved successfully");

        return "redirect:/admin-author";
    }

    @GetMapping("/admin-author/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Author author = service.get(id);
            model.addAttribute("category", author);
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
