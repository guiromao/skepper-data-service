package co.skepper.controllers;

import co.skepper.models.Book;
import co.skepper.services.books.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity printBookOnConsole(@PathVariable Long bookId){
        booksService.printBookOnConsole(bookId);

        return new ResponseEntity(HttpStatus.OK);
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

}
