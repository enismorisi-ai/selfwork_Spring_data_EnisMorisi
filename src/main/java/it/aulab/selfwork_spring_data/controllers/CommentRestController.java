package it.aulab.selfwork_spring_data.controllers;

import it.aulab.selfwork_spring_data.repositories.AuthorRepository;
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

import it.aulab.selfwork_spring_data.models.Comment;
import it.aulab.selfwork_spring_data.models.Post;
import it.aulab.selfwork_spring_data.repositories.CommentRepository;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    @Autowired
    CommentRepository commentRepository;

    @GetMapping //ok
    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    @GetMapping("/{id}") //ok
    public Comment getComment(@PathVariable("id") Long id){
        return commentRepository.findById(id).get();
    }
   
    @PostMapping //ok
    public Comment createComment(@RequestBody Comment comment){
        return commentRepository.save(comment);
    }

    @PutMapping("/{id}") //ok
    public Comment updateComment(@PathVariable("id") Long id, @RequestBody Comment comment){
        comment.setId(id);
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}") //ok
    public void removeComment(@PathVariable("id") Long id){
        if(commentRepository.existsById(id)){
            commentRepository.deleteById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commento non trovato");
        }
    }
}
