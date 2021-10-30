package avadamedia.kinocms.repository.common;

import avadamedia.kinocms.model.common.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    @Query(value = "SELECT MIN(id) FROM image i WHERE i.current_movie_id = :currentMovieId", nativeQuery = true)
    Long getFirstCurrentMovieId(Long currentMovieId);
}
