package co.skepper.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name="books")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonIgnore
    @OneToMany(mappedBy="book")
    private List<Page> pages;

    private String fileType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    public Book(){

    }

    public Book(String title, List<Page> pages, String type, Author author){
        this.title = title;
        this.pages = pages;
        this.fileType = type;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public List<Page> getPages() {
        return pages;
    }

    public String getFileType() {
        return fileType;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

