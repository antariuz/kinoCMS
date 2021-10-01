package avadamedia.kinocms.service;

import avadamedia.kinocms.model.movies.ComingMovie;

public interface ComingMovieService {

    void createComingMovie(ComingMovie comingMovie);

    void updateComingMovie(ComingMovie comingMovie);

    void deleteComingMovieById(Long id);

    Iterable<ComingMovie> getAllComingMovies();

    ComingMovie getComingMovieById(Long id);

}
