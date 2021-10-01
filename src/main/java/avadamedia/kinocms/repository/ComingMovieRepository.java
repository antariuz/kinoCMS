package avadamedia.kinocms.repository;

import avadamedia.kinocms.model.movies.ComingMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComingMovieRepository extends CrudRepository<ComingMovie, Long> {
}
