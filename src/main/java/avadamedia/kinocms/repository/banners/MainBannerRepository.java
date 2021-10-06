package avadamedia.kinocms.repository.banners;

import avadamedia.kinocms.model.banners.MainBanner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainBannerRepository extends CrudRepository<MainBanner, Long> {
}
