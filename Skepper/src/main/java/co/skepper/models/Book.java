package co.skepper.models;

import javax.persistence.*;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    public Book(){

    }

    public Book(String title, String text, Author author){
        this.title = title;
        this.text = text;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

