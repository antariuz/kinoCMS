package avadamedia.kinocms.repository;

import avadamedia.kinocms.model.promotions.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Long> {
}
