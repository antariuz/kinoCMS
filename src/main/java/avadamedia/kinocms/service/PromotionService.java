package avadamedia.kinocms.service;

import avadamedia.kinocms.model.promotions.Promotion;

public interface PromotionService {

    void createPromotion(Promotion promotion);

    void updatePromotion(Promotion promotion);

    void deletePromotionById(Long id);

    Iterable<Promotion> getAllPromotions();

    Promotion getPromotionById(Long id);

}
