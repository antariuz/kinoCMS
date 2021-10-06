package avadamedia.kinocms.repository.pages;

import avadamedia.kinocms.model.pages.NewPage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewPageRepository extends CrudRepository<NewPage, Long> {
}
