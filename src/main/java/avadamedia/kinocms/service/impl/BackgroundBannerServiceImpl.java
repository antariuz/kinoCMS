package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.banners.BackgroundBanner;
import avadamedia.kinocms.repository.banners.BackgroundBannerRepository;
import avadamedia.kinocms.service.BackgroundBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BackgroundBannerServiceImpl implements BackgroundBannerService {

    private final BackgroundBannerRepository repository;

    @Override
    public void createBackgroundBanner(BackgroundBanner backgroundBanner) {
        repository.save(backgroundBanner);
    }

    @Override
    public void updateBackgroundBanner(BackgroundBanner backgroundBanner) {
        repository.save(backgroundBanner);
    }

    @Override
    public void deleteBackgroundBannerById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<BackgroundBanner> getAllBackgroundBanners() {
        return repository.findAll();
    }

    @Override
    public BackgroundBanner getBackgroundBannerById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Long getMaxId() {
        return repository.getMaxId();
    }

}
