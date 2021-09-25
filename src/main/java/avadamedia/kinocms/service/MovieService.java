package avadamedia.kinocms.service;

import avadamedia.kinocms.model.Movie;

public interface MovieService {

    void createMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovieById(Long id);

    Iterable<Movie> getAllMovies();

    Movie getMovieById(Long id);

}
