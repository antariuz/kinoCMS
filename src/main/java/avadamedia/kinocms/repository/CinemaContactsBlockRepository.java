package avadamedia.kinocms.repository;

import avadamedia.kinocms.model.pages.assist.CinemaContactsBlock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaContactsBlockRepository extends CrudRepository<CinemaContactsBlock, Long> {
    @Query(value = "SELECT MAX(id) FROM cinema_contacts_block", nativeQuery = true)
    Long getLastId();
}
