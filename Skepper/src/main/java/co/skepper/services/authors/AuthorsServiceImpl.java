package co.skepper.services.authors;

import co.skepper.models.Author;
import co.skepper.models.Book;
import co.skepper.models.Page;
import co.skepper.repositories.AuthorsRepository;
import co.skepper.repositories.BooksRepository;
import co.skepper.repositories.PagesRepository;
import co.skepper.services.authors.AuthorsService;
import co.skepper.utils.FileUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

@Service
public class AuthorsServiceImpl implements AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private PagesRepository pagesRepository;

    @Override
    public List<Author> findAll() {
        return authorsRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorsRepository.findById(id);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorsRepository.saveAndFlush(author);
    }

    @Override
    public Author addBook(Long id, String title, MultipartFile bookFile) {
        Author author = findById(id).isPresent() ? findById(id).get() : null;

        if (author != null) {

            Book book = new Book();
            book.setAuthor(author);
            book.setTitle(title);
            book.setFileType(extractType(bookFile.getOriginalFilename()));
            String content = FileUtils.readDocFile(bookFile);

            List<Page> pages = contentToPages(content, book);

            book.setPages(pages);

            booksRepository.saveAndFlush(book);

            pages.stream()
                    .forEach(page -> pagesRepository.saveAndFlush(page));


            author.addBook(book);
            author = authorsRepository.saveAndFlush(author);

        }

        return author;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorsRepository.deleteById(id);
    }

    private List<Page> contentToPages(String content, Book book) {
        List<Page> result = new ArrayList<>();
        int pageIndex = 0;
        String text = "";

        result.add(new Page());
        result.get(0).setBook(book);

        for (int i = 0; i < content.length(); i++) {
            text += content.charAt(i);

            if (i % Page.MAX_CHARACTERS == 0) {
                result.get(pageIndex).setContent(text);
                text = "";
                pageIndex++;
                result.add(new Page());
                result.get(pageIndex).setBook(book);
            }
        }

        if (!text.equals("")) {
            result.get(pageIndex).setContent(text);
        }

        return result;
    }

    private String extractType(String filename) {
        int dotIndex = filename.lastIndexOf('.');

        return filename.substring(dotIndex + 1).trim().toLowerCase();
    }

}
