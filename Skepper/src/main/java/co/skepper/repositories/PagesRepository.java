package co.skepper.repositories;

import co.skepper.models.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagesRepository extends JpaRepository<Page, Long> {

}
