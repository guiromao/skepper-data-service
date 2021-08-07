package co.skepper.repositories;

import co.skepper.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContains(String text);

}
