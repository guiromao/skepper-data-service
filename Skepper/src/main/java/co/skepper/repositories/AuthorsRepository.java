package co.skepper.repositories;

import co.skepper.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Author, Long> {

        List<Author> findByNameContains(String name);

}
