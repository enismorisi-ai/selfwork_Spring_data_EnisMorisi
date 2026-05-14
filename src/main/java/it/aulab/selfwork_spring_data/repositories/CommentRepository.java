package it.aulab.selfwork_spring_data.repositories;

import org.springframework.data.repository.CrudRepository;

import it.aulab.selfwork_spring_data.models.Comment;

public interface CommentRepository extends CrudRepository<Comment,Long>{

}
