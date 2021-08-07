package co.skepper.models;

import co.skepper.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="books")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Set<Genre> genres;

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

    public Book(String title, List<Page> pages, Set<Genre> genres, String type, Author author){
        this.title = title;
        this.pages = pages;
        this.genres = genres;
        this.fileType = type;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public List<Page> getPages() {
        return pages;
    }

    public Set<Genre> getGenres() {
        return genres;
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

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
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

