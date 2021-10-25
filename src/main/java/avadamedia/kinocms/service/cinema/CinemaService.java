package avadamedia.kinocms.service.cinema;

import avadamedia.kinocms.model.cinemas.Cinema;

public interface CinemaService {

    void createCinema(Cinema cinema);

    void updateCinema(Cinema cinema);

    void deleteCinemaById(Long id);

    Iterable<Cinema> getAllCinemas();

    Cinema getCinemaById(Long id);

    Long getLastId();

}
