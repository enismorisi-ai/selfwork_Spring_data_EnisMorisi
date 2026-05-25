package it.aulab.selfwork_spring_data.controllers;

import it.aulab.selfwork_spring_data.repositories.AuthorRepository;
import it.aulab.selfwork_spring_data.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.aulab.selfwork_spring_data.dtos.PostDto;
import it.aulab.selfwork_spring_data.models.Post;
import it.aulab.selfwork_spring_data.services.CrudService;
import it.aulab.selfwork_spring_data.services.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    // @Autowired
    // PostService postService;
    @Autowired
    CrudService<PostDto, Post, Long> postService;

    @Autowired
    AuthorRepository authorRepository;

    PostController(AuthorRepository authorRepository, PostRepository postRepository) {
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
    }

    @GetMapping
    public String postsView(Model viewModel){
        viewModel.addAttribute("title", "All Posts");
        viewModel.addAttribute("posts", postService.readAll());
        return "posts";
    }

    @GetMapping("/create")
    public String createPost(Model viewModel){
        viewModel.addAttribute("title", "Create a new Post");
        viewModel.addAttribute("post", new Post());
        viewModel.addAttribute("authors", authorRepository.findAll());
        return "createPost";
    }

    @PostMapping
    public String addPost(@ModelAttribute("post") Post post){
        postService.create(post);
        return "redirect:/posts";
    }

    @GetMapping("/remove/{id}")
    public String removePost(@PathVariable("id") Long id){
        postService.delete(id);
        return "redirect:/posts";
    }    

    @GetMapping("/update/{id}")
    public String modifyPost(@PathVariable("id") Long id, Model viewModel){
        viewModel.addAttribute("title", "Edit post");
        viewModel.addAttribute("post", postService.read(id));
        viewModel.addAttribute("authors", authorRepository.findAll());
        return "updatePost";
    }

    // Ho creato la edit (cioe la vista per modificare ol post, devo procedere con l'update)
    
}
