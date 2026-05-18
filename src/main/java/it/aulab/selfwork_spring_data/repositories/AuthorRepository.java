package it.aulab.selfwork_spring_data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import it.aulab.selfwork_spring_data.models.Author;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {
   List<Author> findByName(String firstname);
   List<Author> findBySurname(String lastname);
   List<Author> findByNameAndSurname(String firstname, String lastname);
   
    Author findByEmail(String email);

   @Query(value="select * from authors a where a.firstname='Giuseppe'", nativeQuery=true)
   List<Author> authorsWithSameName();

   @Query("select a from Author a where a.name='Giuseppe'")
   List<Author> authorWithSameNameNotNative();
}
