package it.aulab.selfwork_spring_data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.aulab.selfwork_spring_data.dtos.CommentDto;
import it.aulab.selfwork_spring_data.models.Comment;
import it.aulab.selfwork_spring_data.repositories.PostRepository;
import it.aulab.selfwork_spring_data.services.CrudService;


@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    @Qualifier("commentService")
    private CrudService<CommentDto,Comment,Long> commentService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String commentsView(Model viewModel){
        viewModel.addAttribute("title", "All comments");
        viewModel.addAttribute("comments", commentService.readAll());
        return "comments";
    }

    @GetMapping("/create")
    public String createComment(Model viewModel){
        viewModel.addAttribute("title","All comments");
        viewModel.addAttribute("comment", new Comment());
        viewModel.addAttribute("posts", postRepository.findAll());
        return "createComment";
    }

    @PostMapping
    public String addComment(Comment comment){
        commentService.create(comment);
        return "redirect:/comments";
    }

    //remove
    @GetMapping("/remove/{id}")
    public String removeComment(@PathVariable("id") Long id){
        commentService.delete(id);
        return "redirect:/comments";
    }

    //edit
    @GetMapping("/update/{id}")
    public String modifyComment(@PathVariable("id") Long id, Model viewModel){
        viewModel.addAttribute("title", "edit a comment");
        viewModel.addAttribute("comment", commentService.read(id));
        viewModel.addAttribute("posts", postRepository.findAll());
        return "updateComment";
    }

    @PostMapping("/update")
    public String updateComment(@ModelAttribute("comment") Comment comment){
        commentService.update(comment.getId(), comment);
        return "redirect:/comments";
    } 

}
