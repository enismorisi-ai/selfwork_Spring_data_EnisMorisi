package it.aulab.selfwork_spring_data.services;

import java.util.List;

import it.aulab.selfwork_spring_data.dtos.AuthorDto;
import it.aulab.selfwork_spring_data.models.Author;

public interface AuthorService {
    List<AuthorDto> readAll();
    AuthorDto read(Long id);
    List<AuthorDto> read(String email);
    List<AuthorDto> read(String firstname, String lastname);
    AuthorDto create(Author author);
    AuthorDto update(Long id, Author author);
    void delete(Long id);
}
