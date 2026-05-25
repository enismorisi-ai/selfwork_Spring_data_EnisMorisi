package it.aulab.selfwork_spring_data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import it.aulab.selfwork_spring_data.models.Post;

public interface PostRepository extends ListCrudRepository<Post,Long> {
    //magari posso aggiungere un metodo che mi restituisca una lista di commenti e uno che mi dia l'autore del post
}
