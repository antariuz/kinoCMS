package avadamedia.kinocms.controller;

import avadamedia.kinocms.service.movie.ComingMovieService;
import avadamedia.kinocms.service.movie.CurrentMovieService;
import avadamedia.kinocms.service.banner.MainBannerService;
import avadamedia.kinocms.service.banner.NewsBannerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final MainBannerService mainBannerService;
    private final CurrentMovieService currentMovieService;
    private final ComingMovieService comingMovieService;
    private final NewsBannerService newsBannerService;

    @RequestMapping({"/", ""})
    public ModelAndView showHomePage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        mav.addObject("currentMovies", currentMovieService.getAllCurrentMovies());
        mav.addObject("comingMovies", comingMovieService.getAllComingMovies());
        mav.addObject("newsBanners", newsBannerService.getAllNewsBanners());
        mav.addObject("firstNewsBanner", newsBannerService.getFirstId());
        return mav;
    }

    @RequestMapping({"admin/dashboard","admin","admin/"})
    public String showAdminPage(){
        return "admin/dashboard";
    }

    @RequestMapping({"poster","poster/"})
    public ModelAndView showPosterPage(){
        ModelAndView mav = new ModelAndView("public/poster");
        mav.addObject("currentMovies", currentMovieService.getAllCurrentMovies());
        return mav;
    }

    @RequestMapping({"schedule","schedule/"})
    public ModelAndView showSchedulePage(){
        ModelAndView mav = new ModelAndView("public/schedule");

        return mav;
    }

    @RequestMapping({"soon","soon/"})
    public ModelAndView showSoonPage(){
        ModelAndView mav = new ModelAndView("public/soon");
        mav.addObject("comingMovies", comingMovieService.getAllComingMovies());
        return mav;
    }


    @RequestMapping({"cinemas","cinemas/"})
    public ModelAndView showCinemasPage(){
        ModelAndView mav = new ModelAndView("public/cinemas");
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        return mav;
    }

    @RequestMapping({"promotions","promotions/"})
    public ModelAndView showPromotionsPage(){
        ModelAndView mav = new ModelAndView("public/promotions");
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        return mav;
    }

    @RequestMapping({"news","news/"})
    public ModelAndView showNewsPage(){
        ModelAndView mav = new ModelAndView("public/news");
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        return mav;
    }

    @RequestMapping({"ad","ad/"})
    public ModelAndView showAdPage(){
        ModelAndView mav = new ModelAndView("public/ad");

        return mav;
    }

    @RequestMapping({"cafe","cafe/"})
    public ModelAndView showCafePage(){
        ModelAndView mav = new ModelAndView("public/cafe");

        return mav;
    }

    @RequestMapping({"apps","apps/"})
    public ModelAndView showAppsPage(){
        ModelAndView mav = new ModelAndView("public/apps");

        return mav;
    }

    @RequestMapping({"contacts","contacts/"})
    public ModelAndView showContactsPage(){
        ModelAndView mav = new ModelAndView("public/contacts");

        return mav;
    }

}
