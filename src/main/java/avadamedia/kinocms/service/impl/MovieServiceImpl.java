package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.Movie;
import avadamedia.kinocms.repository.MovieRepository;
import avadamedia.kinocms.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Override
    public void createMovie(Movie movie) {
        repository.save(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        repository.save(movie);
    }

    @Override
    public void deleteMovieById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Movie> getAllMovies() {
        return repository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
