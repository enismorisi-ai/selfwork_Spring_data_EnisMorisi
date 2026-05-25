package it.aulab.selfwork_spring_data.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.selfwork_spring_data.dtos.PostDto;
import it.aulab.selfwork_spring_data.models.Post;
import it.aulab.selfwork_spring_data.repositories.PostRepository;

@Service
public class PostService implements CrudService<PostDto,Post,Long>{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PostDto> readAll() {
        List<PostDto> dtos = new ArrayList<PostDto>();
        for(Post post : postRepository.findAll()){
            dtos.add(mapper.map(post, PostDto.class));
        }
        return dtos;
    }

    @Override
    public PostDto read(Long id) {
        Optional<Post> optPost = postRepository.findById(id);
        if(optPost.isPresent()){
            return mapper.map(optPost.get(), PostDto.class);
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        }
    }

    @Override
    public PostDto create(Post post) {
        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto update(Long id, Post post) {
        if(postRepository.existsById(id)){
            post.setId(id);
            return mapper.map(postRepository.save(post), PostDto.class);
        } else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
    
}
