package co.skepper.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="books")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonIgnore
    @Lob
    private Blob textFile;

    private String fileType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    public Book(){

    }

    public Book(String title, Blob file, String type, Author author){
        this.title = title;
        this.textFile = file;
        this.fileType = type;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public Blob getTextFile() {
        return textFile;
    }

    public String getFileType() {
        return fileType;
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

    public void setTextFile(Blob text) {
        this.textFile = text;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

