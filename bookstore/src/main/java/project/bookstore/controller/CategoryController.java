package project.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.bookstore.entity.Category;
import project.bookstore.service.CategoryNotFoundException;
import project.bookstore.service.CategoryService;

@Controller
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("Admin/admin-category")
    public String showBookCategory(Model model) {
        List<Category> listCategories = service.listAll();
        model.addAttribute("listCategories", listCategories);

        return "Admin/admin-category";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add New Category");
        return "Admin/admin-add-category";
    }

    @GetMapping("/save")
    public String saveCategory(Category category, RedirectAttributes ra) {
        service.save(category);
        ra.addFlashAttribute("message", "The category has been saved successfully");
        return "redirect:/Admin/admin-category";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            Category category = service.get(id);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Edit Category (ID: " + id + ")");
        } catch (CategoryNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/Admin/admin-category";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The category ID " + id + " has been deleted");
        } catch (CategoryNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/Admin/admin-category";
    }
}
