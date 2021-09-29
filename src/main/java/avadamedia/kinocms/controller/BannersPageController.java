package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.banners.BackgroundBanner;
import avadamedia.kinocms.service.BackgroundBannerService;
import avadamedia.kinocms.service.MainBannerService;
import avadamedia.kinocms.service.NewsBannerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/banners/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BannersPageController {

    private final MainBannerService mainBannerService;
    private final BackgroundBannerService backgroundBannerService;
    private final NewsBannerService newsBannerService;

    //    Show all Banners
    @GetMapping({"/", ""})
    public ModelAndView showAllBanners() {
        ModelAndView mav = new ModelAndView("/admin/banners/index");
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("backgroundBanner", getBackgroundBanner());
        mav.addObject("newsBanners", newsBannerService.getAllNewsBanners());
        return mav;
    }

    public BackgroundBanner getBackgroundBanner() {
        Long lastId = backgroundBannerService.getMaxId();
        if (lastId == null) {
            backgroundBannerService.createBackgroundBanner(new BackgroundBanner());
        }
        return backgroundBannerService.getBackgroundBannerById(lastId);
    }

}