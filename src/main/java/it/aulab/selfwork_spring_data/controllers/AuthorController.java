package it.aulab.selfwork_spring_data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.selfwork_spring_data.models.Author;
import it.aulab.selfwork_spring_data.models.Post;
import it.aulab.selfwork_spring_data.repositories.AuthorRepository;


@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable("id") Long id) {
        return authorRepository.findById(id).get();
    }
    
    @PostMapping
    public Author createAuthor(@RequestBody Author author){
        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable("id") Long id,  @RequestBody Author author){
        author.setId(id);
        return authorRepository.save(author);
    }

    @DeleteMapping("/{id}")
    public void removeAuthor(@PathVariable("id") Long id){
        if(authorRepository.existsById(id)){
            Author author = authorRepository.findById(id).get();
            List<Post> authorPosts = author.getPosts();
            for(Post p : authorPosts){
                p.setAuthor(null);
            }

            authorRepository.deleteById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autore non trovato");
        }
    }
}
