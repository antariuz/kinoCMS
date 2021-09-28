package avadamedia.kinocms.service;

import avadamedia.kinocms.model.banners.MainBanner;

public interface MainBannerService {

    MainBanner createMainBanner(MainBanner mainBanner);

    void updateMainBanner(MainBanner mainBanner);

    void deleteMainBannerById(Long id);

    Iterable<MainBanner> getAllMainBanners();

    MainBanner getMainBannerById(Long id);

}
