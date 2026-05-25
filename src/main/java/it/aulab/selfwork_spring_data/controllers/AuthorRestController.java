package it.aulab.selfwork_spring_data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aulab.selfwork_spring_data.dtos.AuthorDto;
import it.aulab.selfwork_spring_data.models.Author;
import it.aulab.selfwork_spring_data.repositories.AuthorRepository;
import it.aulab.selfwork_spring_data.services.AuthorService;


@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService; 

    @GetMapping
    public List<AuthorDto> getAllAuthors(){
        return authorService.readAll();
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable("id") Long id) {
        return authorService.read(id);
    }
    
    @PostMapping
    public AuthorDto createAuthor(@RequestBody Author author){
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@PathVariable("id") Long id,  @RequestBody Author author){
        return authorService.update(id, author);
    }

    @DeleteMapping("/{id}")
    public void removeAuthor(@PathVariable("id") Long id){
        authorService.delete(id);
    }
}
