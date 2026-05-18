package it.aulab.selfwork_spring_data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import it.aulab.selfwork_spring_data.models.Comment;

public interface CommentRepository extends ListCrudRepository<Comment,Long>{

}
