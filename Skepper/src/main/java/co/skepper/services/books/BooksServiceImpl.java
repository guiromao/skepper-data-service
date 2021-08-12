package co.skepper.services.books;

import co.skepper.enums.Genre;
import co.skepper.models.Book;
import co.skepper.repositories.BooksRepository;
import co.skepper.services.books.BooksService;
import co.skepper.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Override
    public List<Book> findByTitle(String text) {
        return booksRepository.findByTitleContains(text);
    }

    @Override
    public List<Book> findLongBooks() {
        return booksRepository.searchLongBooks();
    }

    @Override
    public Book editGenres(Long bookId, Set<Genre> genres) {
        Optional<Book> maybeBook = booksRepository.findById(bookId);
        Book book = maybeBook.orElse(null);

        if(null != book){
            genres.forEach((genre) -> {
                if(genre != null){
                    book.addGenre(genre);
                }
            });
            booksRepository.saveAndFlush(book);
        }

        return book;
    }

    @Override
    public void incrementViews(Long bookId) {
        Book book = booksRepository.findById(bookId).orElse(null);

        if(book != null){
            book.incrementViews();
            booksRepository.saveAndFlush(book);
        }
    }

    @Override
    public Map<String, String> pagesOfBook(Long bookId) {
        Map<String, String> pagesMap = new HashMap<>();

        Book book = booksRepository.findById(bookId).orElseGet(null);

        if(book != null){
            pagesMap.put("Title", book.getTitle());
            pagesMap.put("Number of Pages", String.valueOf(book.getPages().size()));
        }

        return pagesMap;
    }

}
