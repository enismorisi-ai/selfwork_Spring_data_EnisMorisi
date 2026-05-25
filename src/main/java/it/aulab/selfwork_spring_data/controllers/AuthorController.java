package it.aulab.selfwork_spring_data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.aulab.selfwork_spring_data.models.Author;
import it.aulab.selfwork_spring_data.services.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public String authorsView(Model viewModel){
        viewModel.addAttribute("title", "Authors");
        viewModel.addAttribute("authors", authorService.readAll());
        return "authors";
    }

    @GetMapping("/create")
    public String createAuthorView(Model viewModel){
        viewModel.addAttribute("title", "Create author");
        viewModel.addAttribute("author", new Author());
        return "createAuthor";
    }


    @PostMapping
    public String createAuthor(Author author){
        authorService.create(author);
        return "redirect:/authors";
    }
}
