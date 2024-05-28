package project.bookstore.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import project.bookstore.entity.Book;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.service.BookService;
import project.bookstore.service.UserService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin-books")
    public String getAllBook(Model model) {
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long userId = userDetails.getUserId();

        List<Book> listBooks = bookService.getAllBook();
        model.addAttribute("listBooks", listBooks);
//        model.addAttribute("userId", userId);
        return "Admin/admin-books";
    }

    @RequestMapping("/books-detail/{id}")
    public String getBookDetail(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "Client/books-detail";
    }

    @RequestMapping("/admin-books/edit/{id}")
    public String editBook(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("pageTitle", "Edit Book (ID: " + id + ")");
        return "Admin/admin-add-book";
    }

    @RequestMapping("/admin-books/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteById(id);
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
        bookService.save(book);
        return "redirect:/admin-books";
    }

}
