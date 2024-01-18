package project.bookstore.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import project.bookstore.entity.Book;
import project.bookstore.entity.MyBookList;
import project.bookstore.service.BookService;
import project.bookstore.service.MyBookListService;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBook() {
        List<Book> list = service.getAllBook();
        // ModelAndView modelAndView = new ModelAndView();
        // modelAndView.setViewName("bookList");
        // modelAndView.addObject("book", list);
        // return modelAndView;
        return new ModelAndView("bookList", "book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book) {
        service.save(book);
        return "redirect:/available_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
        List<MyBookList> bookList = myBookService.getAllMyBooks();
        model.addAttribute("book", bookList);
        return "myBooks";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book book = service.getBookById(id);

        MyBookList bookList = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
        
        myBookService.saveMyBooks(bookList);

        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}

}
