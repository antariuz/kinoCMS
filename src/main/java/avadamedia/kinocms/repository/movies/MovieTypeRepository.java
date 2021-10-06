package avadamedia.kinocms.repository.movies;

import avadamedia.kinocms.model.movies.assist.MovieType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTypeRepository extends CrudRepository<MovieType, Long> {
    @Query(value = "SELECT MAX(id) FROM movie_type", nativeQuery = true)
    Long getMaxId();
}
