package avadamedia.kinocms.repository;

import avadamedia.kinocms.model.banners.MainBanner;
import avadamedia.kinocms.model.banners.NewsBanner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsBannerRepository extends CrudRepository<NewsBanner, Long> {
}
