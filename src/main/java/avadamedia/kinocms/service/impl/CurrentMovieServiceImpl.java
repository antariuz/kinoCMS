package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.movies.CurrentMovie;
import avadamedia.kinocms.repository.CurrentMovieRepository;
import avadamedia.kinocms.service.CurrentMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentMovieServiceImpl implements CurrentMovieService {

    private final CurrentMovieRepository repository;

    @Override
    public void createCurrentMovie(CurrentMovie currentMovie) {
        repository.save(currentMovie);
    }

    @Override
    public void updateCurrentMovie(CurrentMovie currentMovie) {
        repository.save(currentMovie);
    }

    @Override
    public void deleteCurrentMovieById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<CurrentMovie> getAllCurrentMovies() {
        return repository.findAll();
    }

    @Override
    public CurrentMovie getCurrentMovieById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
