package avadamedia.kinocms.repository.mailings.assist;

import avadamedia.kinocms.model.mailing.EmailMailing;
import avadamedia.kinocms.model.mailing.assist.Template;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {
    @Query(value = "SELECT MAX(id) FROM template", nativeQuery = true)
    Long getLastId();
    @Query(value = "SELECT * FROM template ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Template> getLastFiveId();
}
