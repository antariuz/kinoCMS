package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.promotions.Promotion;
import avadamedia.kinocms.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("admin/promotions")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PromotionController {

    private final PromotionService service;

    //    Show all Promotions
    @GetMapping({"/", ""})
    public ModelAndView promotions() {
        return new ModelAndView("/admin/promotions/index", "promotions", service.getAllPromotions());
    }


    @GetMapping("add")
    public String addPromotion(Model model) {
        model.addAttribute("promotion", new Promotion());
        model.addAttribute("seo", new SEO());
        return "/admin/promotions/add";
    }

    @PostMapping("add")
    public String addCurrentMovie(Promotion promotion,
                                  @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        promotion.setMainImage(fileName);
        service.createPromotion(promotion);
        String uploadDir = "promotion/" + promotion.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/promotions";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteCurrentMovie(@PathVariable("id") Long id) {
        service.deletePromotionById(id);
        return "redirect:/admin/promotions";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateCurrentMovie(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/promotions/update");
        mav.addObject("promotion", service.getPromotionById(id));
        mav.addObject("seo", new SEO());
        return mav;
    }

    @PutMapping("update")
    public String updateCurrentMovie(Promotion promotion,
                                     @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        promotion.setMainImage(fileName);
        service.updatePromotion(promotion);
        String uploadDir = "promotion/" + promotion.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/promotions";
    }

}