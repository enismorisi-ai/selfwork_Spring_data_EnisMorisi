package it.aulab.selfwork_spring_data.repositories;

import org.springframework.data.repository.CrudRepository;

import it.aulab.selfwork_spring_data.models.Post;

public interface PostRepository extends CrudRepository<Post,Long> {

}
