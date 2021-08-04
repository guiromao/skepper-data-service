package co.skepper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="pages")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Page {

    public static final int MAX_CHARACTERS = 250;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageId;
    private String content;

    @ManyToOne
    @JoinColumn(name="id")
    private Book book;

    public Page(){

    }

    public Page(String content, Book book){
        this.content = content;
        this.book = book;
    }

    public Long getPageId() {
        return pageId;
    }

    public String getContent() {
        return content;
    }

    public Book getBook() {
        return book;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
