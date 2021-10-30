package avadamedia.kinocms.service.banner;

import avadamedia.kinocms.model.banners.MainBanner;

public interface MainBannerService {

    void createMainBanner(MainBanner mainBanner);

    void updateMainBanner(MainBanner mainBanner);

    void deleteMainBannerById(Long id);

    Iterable<MainBanner> getAllMainBanners();

    MainBanner getMainBannerById(Long id);

    Long getFirstId();

    Long getLastId();

}
