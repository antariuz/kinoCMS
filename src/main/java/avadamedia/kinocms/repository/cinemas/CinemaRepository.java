package avadamedia.kinocms.repository.cinemas;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.movies.CurrentMovie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    @Query(value = "SELECT MAX(id) FROM cinema", nativeQuery = true)
    Long getLastId();
}
