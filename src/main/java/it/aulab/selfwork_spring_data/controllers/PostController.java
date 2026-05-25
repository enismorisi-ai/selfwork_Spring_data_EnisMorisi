package it.aulab.selfwork_spring_data.controllers;

import it.aulab.selfwork_spring_data.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/posts")
public class PostController {

    // @Autowired
    // PostService postService;
    @Autowired
    CrudService<PostDto, Post, Long> postService;

    @Autowired
    AuthorRepository authorRepository;

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

    // remove
    @GetMapping("/remove/{id}")
    public String removePost(@PathVariable("id") Long id){
        postService.delete(id);
        return "redirect:/posts";
    }    

    // edit
    @GetMapping("/update/{id}")
    public String modifyPost(@PathVariable("id") Long id, Model viewModel){
        viewModel.addAttribute("title", "Edit post");
        viewModel.addAttribute("post", postService.read(id));
        viewModel.addAttribute("authors", authorRepository.findAll());
        return "updatePost";
    }

    // Ho creato la edit (cioe la vista per modificare il post, devo procedere con l'update)

    @PostMapping("update")
    public String updateProduct(@ModelAttribute("post") Post post){
        postService.update(post.getId(), post);
        return "redirect:/posts";
    }
}
