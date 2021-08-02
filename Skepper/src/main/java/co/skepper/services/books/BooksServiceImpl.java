package co.skepper.services.books;

import co.skepper.models.Book;
import co.skepper.repositories.BooksRepository;
import co.skepper.services.books.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return booksRepository.findById(bookId);
    }

    @Override
    public Book saveBook(Book book) {
        return booksRepository.saveAndFlush(book);
    }

    @Override
    public void deleteBook(Long id) {
        booksRepository.deleteById(id);
    }
}
