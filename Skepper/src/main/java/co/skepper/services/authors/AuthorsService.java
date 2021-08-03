package co.skepper.services.authors;

import co.skepper.models.Author;
import co.skepper.models.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    List<Author> findAll();

    Author saveAuthor(Author author);

    Author addBook(Long authorId, String title, MultipartFile book);

    Optional<Author> findById(Long id);

    void deleteAuthor(Long id);

}
