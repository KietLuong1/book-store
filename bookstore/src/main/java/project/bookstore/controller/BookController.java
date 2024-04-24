package project.bookstore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
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

    @RequestMapping("/books-detail/{id}")
    public String getBookDetail(@PathVariable("id") Integer id, Model model) {
        Book book = service.getBookById(id);
        model.addAttribute("book", book);
        return "Client/books-detail";
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

    @PostMapping("/admin-add-book/save")
    public String addBook(Book book) {
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        book.setBook_imgae(fileName);
//        Book savedBoook = service.save(book);
//
//        String uploadDir = "./Admin/images/" + savedBoook.getBook_id();
//
//        Path uploadPath = Paths.get(uploadDir);
//
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            Path filePath = uploadPath.resolve(fileName);
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new IOException("Could not save uploaded file: " + fileName);
//        }
        service.save(book);
        return "redirect:/admin-books";
    }

}
