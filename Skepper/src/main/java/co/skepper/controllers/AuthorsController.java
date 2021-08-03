package co.skepper.controllers;

import co.skepper.models.Author;
import co.skepper.services.authors.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorsController {

    @Autowired
    private AuthorsService authorsService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Author>> allAuthors() {
        return new ResponseEntity<>(authorsService.findAll(), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity insertAuthor(@RequestBody Author author) {
        HttpStatus status = authorsService.saveAuthor(author) != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return new ResponseEntity(status);
    }

    @PostMapping("/{authorId}/books")
    public ResponseEntity addNewBook(@PathVariable Long authorId, @RequestParam String title,
                                     @RequestParam MultipartFile book) {

        authorsService.addBook(authorId, title, book);

        return new ResponseEntity(HttpStatus.CREATED);
    }


}
