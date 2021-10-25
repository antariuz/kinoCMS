package avadamedia.kinocms.service.impl.cinemas;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.repository.cinemas.CinemaRepository;
import avadamedia.kinocms.service.cinema.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
