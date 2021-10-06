package avadamedia.kinocms.service;

import avadamedia.kinocms.model.movies.assist.MovieType;

public interface MovieTypeService {

    void createMovieType(MovieType movieType);

    void updateMovieType(MovieType movieType);

    void deleteMovieTypeById(Long id);

    Iterable<MovieType> getAllMovieTypes();

    MovieType getMovieTypeById(Long id);

    Long getMaxId();

}
