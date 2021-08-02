package co.skepper.services.authors;

import co.skepper.models.Author;
import co.skepper.repositories.AuthorsRepository;
import co.skepper.services.authors.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorsServiceImpl implements AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;


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
    public void deleteAuthor(Long id) {
        authorsRepository.deleteById(id);
    }

}
