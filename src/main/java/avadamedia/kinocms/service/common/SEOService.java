package avadamedia.kinocms.service.common;

import avadamedia.kinocms.model.common.SEO;

public interface SEOService {

    void createSEO(SEO seo);

    void updateSEO(SEO seo);

    void deleteSEOById(Long id);

    Iterable<SEO> getAllSEO();

    SEO getSEOById(Long id);

}
