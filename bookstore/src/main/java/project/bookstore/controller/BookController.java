package project.bookstore.controller;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.entity.Author;
import project.bookstore.entity.Book;
import project.bookstore.entity.Category;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.exception.CategoryNotFoundException;
import project.bookstore.repository.CategoryRepository;
import project.bookstore.service.*;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private  CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserService userService;

    @Autowired
    private CloudinaryService cloudinaryService;

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
        
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.listAll());
        model.addAttribute("publishers", publisherService.getAllPublisher());
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

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.listAll());
        model.addAttribute("publishers", publisherService.getAllPublisher());
        return "Admin/admin-add-book";
    }

    @PostMapping("/admin-add-book/save")
    public String saveAuthor(@ModelAttribute(name = "book") Book book,
                             @RequestParam("bookImage") MultipartFile multipartFile,
                             @RequestParam(name = "categories") Set<String> categories ) throws IOException {
        try {
            Book savedBook = bookService.save(book);

            String uploadedImageUrl = cloudinaryService.uploadFile(multipartFile, "Admin/books/" + savedBook.getBook_id());
            savedBook.setBook_image(uploadedImageUrl);

            Set<Category> selectedCategory = new HashSet<>();
            for (String category: categories) {
                selectedCategory.add(categoryService.get(Integer.parseInt(category)));
            }
            book.setCategories(selectedCategory);

            System.out.println(categories);
            bookService.save(savedBook);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin-books";
    }

}
