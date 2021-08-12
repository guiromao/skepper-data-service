package co.skepper.services.books;

import co.skepper.enums.Genre;
import co.skepper.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface BooksService {

    List<Book> findAll();

    Optional<Book> findById(Long bookId);

    Book saveBook(Book book);

    void deleteBook(Long id);

    void printBookOnConsole(Long id);

    void sneakPeek(Long bookId);

    List<Book> findByTitle(String text);

    List<Book> findLongBooks();

    Book editGenres(Long bookId, Set<Genre> genres);

    void incrementViews(Long bookId);

    Map<String, String> pagesOfBook(Long bookId);
}
