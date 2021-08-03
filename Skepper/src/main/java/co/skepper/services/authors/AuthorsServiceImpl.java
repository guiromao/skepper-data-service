package co.skepper.services.authors;

import co.skepper.models.Author;
import co.skepper.models.Book;
import co.skepper.repositories.AuthorsRepository;
import co.skepper.repositories.BooksRepository;
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

            try {
                Blob textBlob = new javax.sql.rowset.serial.SerialBlob(bookFile.getBytes());

                Book book = new Book();
                book.setAuthor(author);
                book.setTextFile(textBlob);
                book.setTitle(title);
                book.setFileType(extractType(bookFile.getOriginalFilename()));

                booksRepository.saveAndFlush(book);

                author.addBook(book);
                author = authorsRepository.saveAndFlush(author);

                String content = FileUtils.readDocFile(textBlob, extractType(bookFile.getOriginalFilename()));
                System.out.println(content);

            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch(SQLException e){
                e.printStackTrace();
            }

        }

        return author;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorsRepository.deleteById(id);
    }

    private String extractType(String filename){
        int dotIndex = filename.lastIndexOf('.');

        return filename.substring(dotIndex + 1).trim().toLowerCase();
    }

}
