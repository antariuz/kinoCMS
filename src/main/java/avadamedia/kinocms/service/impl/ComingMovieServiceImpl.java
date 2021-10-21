package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.movies.ComingMovie;
import avadamedia.kinocms.repository.movies.ComingMovieRepository;
import avadamedia.kinocms.service.ComingMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComingMovieServiceImpl implements ComingMovieService {

    private final ComingMovieRepository repository;

    @Override
    public void createComingMovie(ComingMovie comingMovie) {
        repository.save(comingMovie);
    }

    @Override
    public void updateComingMovie(ComingMovie comingMovie) {
        repository.save(comingMovie);
    }

    @Override
    public void deleteComingMovieById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<ComingMovie> getAllComingMovies() {
        return repository.findAll();
    }

    @Override
    public ComingMovie getComingMovieById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Long getLastId() {
        return repository.getLastId();
    }

}
