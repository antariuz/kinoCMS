package avadamedia.kinocms.repository;

import avadamedia.kinocms.model.news.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
}
