package avadamedia.kinocms.repository.cinemas;

import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallRepository extends CrudRepository<CinemaHall, Long> {
}
