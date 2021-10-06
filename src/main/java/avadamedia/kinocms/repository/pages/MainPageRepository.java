package avadamedia.kinocms.repository.pages;

import avadamedia.kinocms.model.pages.MainPage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainPageRepository extends CrudRepository<MainPage, Long> {
}
