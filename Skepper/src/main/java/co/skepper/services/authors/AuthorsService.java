package co.skepper.services.authors;

import co.skepper.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    List<Author> findAll();

    Author saveAuthor(Author author);

    Optional<Author> findById(Long id);

    void deleteAuthor(Long id);

}
