package project.bookstore.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Book;
import project.bookstore.service.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/admin-books")
    public String getAllBook(Model model) {
        List<Book> listBooks = service.getAllBook();
        model.addAttribute("listBooks", listBooks);

        return "Admin/admin-books";
    }

    @GetMapping("/admin-add-book/save")
    public String addBook(Book book) {
        service.save(book);
        return "redirect:/admin-books";
    }

    @RequestMapping("/admin-books/edit/{id}")
    public String editBook(@PathVariable("id") Integer id, Model model) {
        Book book = service.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("pageTitle", "Edit Book (ID: " + id + ")");
        return "Admin/admin-add-book";
    }

    @RequestMapping("/admin-books/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        service.deleteById(id);
        return "redirect:/admin-books";
    }

    @GetMapping("/admin-add-book")
    public String showNewTitle(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle", "Add New Book");
        return "Admin/admin-add-book";
    }

}
