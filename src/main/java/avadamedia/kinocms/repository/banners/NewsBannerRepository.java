package avadamedia.kinocms.repository.banners;

import avadamedia.kinocms.model.banners.MainBanner;
import avadamedia.kinocms.model.banners.NewsBanner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsBannerRepository extends CrudRepository<NewsBanner, Long> {
    @Query(value = "SELECT MIN(id) FROM news_banner", nativeQuery = true)
    Long getFirstId();
}
