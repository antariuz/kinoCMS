package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.promotions.Promotion;
import avadamedia.kinocms.service.PromotionService;
import avadamedia.kinocms.service.common.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/promotions")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PromotionController {

    private final PromotionService promotionService;
    private final ImageService imageService;

    // Show all
    @GetMapping({"/", ""})
    public ModelAndView promotions() {
        return new ModelAndView("admin/promotions/index", "promotions", promotionService.getAllPromotions());
    }

    // Add
    @GetMapping("add")
    public String addPromotion() {
        Promotion promotion = new Promotion();
        promotion.setImages(imageService.initImageList(4));
        promotion.setSeo(new SEO());
        promotionService.createPromotion(promotion);
        return "redirect:/admin/promotions/edit/" + promotion.getId();
    }

    // Update
    @GetMapping("edit/{id}")
    public ModelAndView showEditPromotionPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/promotions/update");
        mav.addObject("promotion", promotionService.getPromotionById(id));
        return mav;
    }

    @PutMapping("update/{id}")
    public String updatePromotion(@PathVariable("id") Long id,
                                  @ModelAttribute("promotion") Promotion promotion,
                                  @RequestParam("image") MultipartFile file,
                                  @RequestParam("galleryImages") MultipartFile[] galleryImages) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "promotions/" + id;
        String galleryImagesUploadDir = "/gallery-images/";
        if (!fileName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            promotion.setMainImage(fileName);
        } else promotion.setMainImage(promotionService.getPromotionById(id).getMainImage());
        List<Image> images = promotionService.getPromotionById(id).getImages();
        if (galleryImages.length != 0) {
            for (int i = 0; i < galleryImages.length; i++) {
                String galleryImageName = StringUtils.cleanPath(galleryImages[i].getOriginalFilename());
                if (!galleryImageName.equals("")) {
                    FileUploadUtil.saveFile(galleryImagesUploadDir, galleryImageName, galleryImages[i]);
                    images.get(i).setName(galleryImageName);
                } else {
                    images.get(i).setName(promotionService.getPromotionById(id).getImages().get(i).getName());
                }
            }
        }
        promotion.setImages(images);
        promotionService.updatePromotion(promotion);
        return "redirect:/admin/promotions/edit/" + id;
    }

    // Delete
    @GetMapping("delete/{id}")
    public String deleteCurrentMovie(@PathVariable("id") Long id) {
        promotionService.deletePromotionById(id);
        return "redirect:/admin/promotions";
    }

}