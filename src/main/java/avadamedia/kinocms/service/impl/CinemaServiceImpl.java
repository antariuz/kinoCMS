package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.repository.cinemas.CinemaRepository;
import avadamedia.kinocms.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository repository;

    @Override
    public void createCinema(Cinema cinema) {
        repository.save(cinema);
    }

    @Override
    public void updateCinema(Cinema cinema) {
        repository.save(cinema);
    }

    @Override
    public void deleteCinemaById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Cinema> getAllCinemas() {
        return repository.findAll();
    }

    @Override
    public Cinema getCinemaById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Long getLastId() {
        return repository.getLastId();
    }

}
