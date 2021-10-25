package avadamedia.kinocms.repository.common;

import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.news.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SEORepository extends CrudRepository<SEO, Long> {
}
