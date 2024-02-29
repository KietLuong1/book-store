package project.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import project.bookstore.entity.Author;
import project.bookstore.repository.AuthorRepository;
import project.bookstore.service.AuthorService;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping("test")
    public ModelAndView getAllBook() {
        List<Author> list = service.getAllAuthor();
        // ModelAndView modelAndView = new ModelAndView();
        // modelAndView.setViewName("bookList");
        // modelAndView.addObject("book", list);
        // return modelAndView;
        return new ModelAndView("Admin/admin-author", "author", list);
    }

    @GetMapping("/save")
    public String addAuthor(@ModelAttribute Author author) {
        service.save(author);
        return "redirect:/Admin/admin-author";
    }

    // @GetMapping("/my_books")
    // public String getMyBooks(Model model) {
    //     List<MyBookList> bookList = myBookService.getAllMyBooks();
    //     model.addAttribute("book", bookList);
    //     return "myBooks";
    // }

    // @RequestMapping("/mylist/{id}")
    // public String getMyList(@PathVariable("id") int id) {
    //     Book book = service.getBookById(id);
    //     MyBookList bookList = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
    //     myBookService.saveMyBooks(bookList);
    //     return "redirect:/my_books";
    // }

    // @RequestMapping("/editBook/{id}")
	// public String editBook(@PathVariable("id") int id,Model model) {
	// 	Book b=service.getBookById(id);
	// 	model.addAttribute("book",b);
	// 	return "bookEdit";
	// }

	// @RequestMapping("/deleteBook/{id}")
	// public String deleteBook(@PathVariable("id")int id) {
	// 	service.deleteById(id);
	// 	return "redirect:/admin-books.html";
	// }
}
