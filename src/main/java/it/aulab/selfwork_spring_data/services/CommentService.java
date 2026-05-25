package it.aulab.selfwork_spring_data.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.selfwork_spring_data.dtos.CommentDto;
import it.aulab.selfwork_spring_data.models.Comment;
import it.aulab.selfwork_spring_data.repositories.CommentRepository;


@Service
public class CommentService implements CrudService<CommentDto, Comment, Long>{

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<CommentDto> readAll() {
        List<CommentDto> dtos = new ArrayList<CommentDto>();

        for(Comment comment : commentRepository.findAll()){
            dtos.add(mapper.map(comment, CommentDto.class));
        }
        return dtos;
    }

    @Override
    public CommentDto read(Long id) {
        Optional<Comment> optComment = commentRepository.findById(id);
        if(optComment.isPresent()){
            return mapper.map(optComment.get(), CommentDto.class);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public CommentDto create(Comment comment) {
        return mapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public CommentDto update(Long id, Comment comment) {
        if(commentRepository.existsById(id)){
            comment.setId(id);
            return mapper.map(commentRepository.save(comment), CommentDto.class);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);   
        }
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

}
