package avadamedia.kinocms.service;

import avadamedia.kinocms.model.movies.CurrentMovie;

public interface CurrentMovieService {

    void createCurrentMovie(CurrentMovie currentMovie);

    void updateCurrentMovie(CurrentMovie currentMovie);

    void deleteCurrentMovieById(Long id);

    Iterable<CurrentMovie> getAllCurrentMovies();

    CurrentMovie getCurrentMovieById(Long id);

    Long getLastId();

}
