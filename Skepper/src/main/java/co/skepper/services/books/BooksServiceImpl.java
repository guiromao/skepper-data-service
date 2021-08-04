package co.skepper.services.books;

import co.skepper.models.Book;
import co.skepper.repositories.BooksRepository;
import co.skepper.services.books.BooksService;
import co.skepper.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public void printBookOnConsole(Long id) {
        Optional<Book> maybeBook = findById(id);

        if(maybeBook.isPresent()){
            Book book = maybeBook.get();
            String content = book.getPages().stream().map(page -> page.getContent()).collect(Collectors.joining());
            System.out.println(content);
        }
    }

    @Override
    public void sneakPeek(Long bookId){
        Optional<Book> maybeBook = findById(bookId);

        if(maybeBook.isPresent()){
            Book book = maybeBook.get();
            int maxPages = (int) Math.round(book.getPages().size() * 0.75);
            int actualPage = (int) Math.ceil(Math.random() * maxPages);
            String content = book.getPages().get(actualPage).getContent();
            System.out.println(content);
        }
    }

}
