package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.banners.MainBanner;
import avadamedia.kinocms.repository.MainBannerRepository;
import avadamedia.kinocms.service.MainBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainBannerServiceImpl implements MainBannerService {

    private final MainBannerRepository repository;

    @Override
    public MainBanner createMainBanner(MainBanner mainBanner) {
       return repository.save(mainBanner);
    }

    @Override
    public void updateMainBanner(MainBanner mainBanner) {
        repository.save(mainBanner);
    }

    @Override
    public void deleteMainBannerById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<MainBanner> getAllMainBanners() {
        return repository.findAll();
    }

    @Override
    public MainBanner getMainBannerById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
