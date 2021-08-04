package co.skepper.repositories;

import co.skepper.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Author, Long> {

}
