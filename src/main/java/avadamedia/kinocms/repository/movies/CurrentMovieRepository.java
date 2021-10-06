package avadamedia.kinocms.repository.movies;

import avadamedia.kinocms.model.movies.CurrentMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentMovieRepository extends CrudRepository<CurrentMovie, Long> {
}
