package co.skepper.repositories;

import co.skepper.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContains(String text);

    @Query("SELECT b FROM Book b WHERE size(b.pages) > 10")
    List<Book> searchLongBooks();

}
