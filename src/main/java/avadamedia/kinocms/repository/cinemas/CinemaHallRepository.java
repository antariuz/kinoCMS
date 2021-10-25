package avadamedia.kinocms.repository.cinemas;

import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallRepository extends CrudRepository<CinemaHall, Long> {
    @Query(value = "SELECT MAX(id) FROM cinema_hall", nativeQuery = true)
    Long getLastId();
}
