package avadamedia.kinocms.repository;

import avadamedia.kinocms.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT MAX(id) FROM users", nativeQuery = true)
    Integer getMaxId();
}
