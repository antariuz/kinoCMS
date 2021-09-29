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
@RequestMapping("admin/movies")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MoviesPageController {

    private final MainBannerService mainBannerService;
    private final BackgroundBannerService backgroundBannerService;

    //    Show all Banners
    @GetMapping({"/", ""})
    public ModelAndView showAllBanners() {
        ModelAndView mav = new ModelAndView("/admin/movies/index");
        mav.addObject("currentMovies", mainBannerService.getAllMainBanners());
        mav.addObject("comingMovies", mainBannerService.getAllMainBanners());
        return mav;
    }


}
