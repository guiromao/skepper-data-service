package co.skepper.services.books;

import co.skepper.models.Book;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    List<Book> findAll();

    Optional<Book> findById(Long bookId);

    Book saveBook(Book book);

    void deleteBook(Long id);

    void printBookOnConsole(Long id);

    void sneakPeek(Long bookId);

    List<Book> findByTitle(String text);
}
