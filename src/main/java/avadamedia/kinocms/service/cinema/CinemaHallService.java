package avadamedia.kinocms.service.cinema;

import avadamedia.kinocms.model.cinemas.assist.CinemaHall;

public interface CinemaHallService {

    void createCinemaHall(CinemaHall cinemaHall);

    void updateCinemaHall(CinemaHall cinemaHall);

    void deleteCinemaHallById(Long id);

    Iterable<CinemaHall> getAllCinemaHalls();

    CinemaHall getCinemaHallById(Long id);

    Long getLastId();

}
