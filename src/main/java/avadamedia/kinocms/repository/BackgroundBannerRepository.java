package avadamedia.kinocms.repository;

import avadamedia.kinocms.model.banners.BackgroundBanner;
import avadamedia.kinocms.model.banners.MainBanner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundBannerRepository extends CrudRepository<BackgroundBanner, Long> {
    @Query(value = "SELECT MAX(id) FROM background_banner", nativeQuery = true)
    Long getMaxId();
}
