package avadamedia.kinocms.repository.pages;

import avadamedia.kinocms.model.pages.ContactsPage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsPageRepository extends CrudRepository<ContactsPage, Long> {
    @Query(value = "SELECT MAX(id) FROM contacts_page", nativeQuery = true)
    Long getMaxId();
}
