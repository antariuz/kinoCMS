package avadamedia.kinocms.repository.pages;

import avadamedia.kinocms.model.pages.ContactsPage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsPageRepository extends CrudRepository<ContactsPage, Long> {
}
