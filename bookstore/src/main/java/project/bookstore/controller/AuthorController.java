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
  
}
