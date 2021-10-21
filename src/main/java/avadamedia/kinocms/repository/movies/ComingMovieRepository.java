package avadamedia.kinocms.repository.movies;

import avadamedia.kinocms.model.movies.ComingMovie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComingMovieRepository extends CrudRepository<ComingMovie, Long> {
    @Query(value = "SELECT MAX(id) FROM coming_movies", nativeQuery = true)
    Long getLastId();
}
