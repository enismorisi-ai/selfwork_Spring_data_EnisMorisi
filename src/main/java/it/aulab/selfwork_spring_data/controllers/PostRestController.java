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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.selfwork_spring_data.models.Comment;
import it.aulab.selfwork_spring_data.models.Post;
import it.aulab.selfwork_spring_data.repositories.CommentRepository;
import it.aulab.selfwork_spring_data.repositories.PostRepository;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping //ok
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/{id}") //ok
    public Post getPost(@PathVariable("id") Long id){
        return postRepository.findById(id).get();
    }

    @PostMapping //ok
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping("/{id}") //ok
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post){
        post.setId(id);
        return postRepository.save(post);
    }

   @DeleteMapping("/{id}") // ok
   public void removePost(@PathVariable("id") Long id){
    if(postRepository.existsById(id)){
        postRepository.deleteById(id);
    }
    else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post non trovato");
    }
   }

}
