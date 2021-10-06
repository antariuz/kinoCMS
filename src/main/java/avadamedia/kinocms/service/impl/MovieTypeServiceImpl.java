package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.movies.assist.MovieType;
import avadamedia.kinocms.repository.movies.MovieTypeRepository;
import avadamedia.kinocms.service.MovieTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieTypeServiceImpl implements MovieTypeService {

    private final MovieTypeRepository repository;

    @Override
    public void createMovieType(MovieType movieType) {
        repository.save(movieType);
    }

    @Override
    public void updateMovieType(MovieType movieType) {
        repository.save(movieType);
    }

    @Override
    public void deleteMovieTypeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<MovieType> getAllMovieTypes() {
        return repository.findAll();
    }

    @Override
    public MovieType getMovieTypeById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Long getMaxId() {
        return repository.getMaxId();
    }

}
