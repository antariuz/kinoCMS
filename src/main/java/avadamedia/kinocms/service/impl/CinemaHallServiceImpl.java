package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import avadamedia.kinocms.repository.cinemas.CinemaHallRepository;
import avadamedia.kinocms.service.CinemaHallService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CinemaHallServiceImpl implements CinemaHallService {

    private final CinemaHallRepository repository;

    @Override
    public void createCinemaHall(CinemaHall cinemaHall) {
        repository.save(cinemaHall);
    }

    @Override
    public void updateCinemaHall(CinemaHall cinemaHall) {
        repository.save(cinemaHall);
    }

    @Override
    public void deleteCinemaHallById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<CinemaHall> getAllCinemaHalls() {
        return repository.findAll();
    }

    @Override
    public CinemaHall getCinemaHallById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
