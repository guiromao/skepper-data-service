package co.skepper.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(){

    }

    public Author(String name, List<Book> books){
        this.name = name;
        this.books = books;
    }

    public Author(String name){
        this.name = name;
        this.books = new ArrayList<>();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setAuthorId(Long id) {
        this.authorId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}

