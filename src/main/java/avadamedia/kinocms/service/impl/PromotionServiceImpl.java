package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.promotions.Promotion;
import avadamedia.kinocms.repository.PromotionRepository;
import avadamedia.kinocms.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository repository;

    @Override
    public void createPromotion(Promotion promotion) {
        repository.save(promotion);
    }

    @Override
    public void updatePromotion(Promotion promotion) {
        repository.save(promotion);
    }

    @Override
    public void deletePromotionById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Promotion> getAllPromotions() {
        return repository.findAll();
    }

    @Override
    public Promotion getPromotionById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
