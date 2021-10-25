package avadamedia.kinocms.service.impl.common;

import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.repository.common.SEORepository;
import avadamedia.kinocms.service.common.SEOService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SEOServiceImpl implements SEOService {

    private final SEORepository repository;

    @Override
    public void createSEO(SEO seo) {
        repository.save(seo);
    }

    @Override
    public void updateSEO(SEO seo) {
        repository.save(seo);
    }

    @Override
    public void deleteSEOById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<SEO> getAllSEO() {
        return repository.findAll();
    }

    @Override
    public SEO getSEOById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
