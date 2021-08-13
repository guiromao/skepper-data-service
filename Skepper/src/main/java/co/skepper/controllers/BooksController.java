package co.skepper.controllers;

import co.skepper.enums.Genre;
import co.skepper.models.Book;
import co.skepper.models.dtos.BookDTO;
import co.skepper.models.dtos.GenresDTO;
import co.skepper.services.books.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api/v1/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping({"/", ""})
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(booksService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Long bookId){
        booksService.printBookOnConsole(bookId);
        booksService.incrementViews(bookId);

        return new ResponseEntity(booksService.findById(bookId), HttpStatus.OK);
    }

    @GetMapping("/{bookId}/sneakpeek")
    public ResponseEntity sneakPeek(@PathVariable Long bookId){
        booksService.sneakPeek(bookId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String title){
        return new ResponseEntity<>(booksService.findByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/long")
    public ResponseEntity<List<Book>> findLongBooks(){
        return new ResponseEntity<>(booksService.findLongBooks(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}/pages")
    public ResponseEntity<Map<String, String>> pagesOfBook(@PathVariable Long bookId){
        return new ResponseEntity<>(booksService.pagesOfBook(bookId), HttpStatus.OK);
    }

    @PutMapping("/{bookId}/genres")
    public ResponseEntity editGenres(@PathVariable Long bookId, @RequestBody GenresDTO genres){
        booksService.editGenres(bookId, genres.getGenres());

        return new ResponseEntity(HttpStatus.OK);
    }

}
