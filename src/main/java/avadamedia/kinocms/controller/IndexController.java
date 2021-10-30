package avadamedia.kinocms.controller;

import avadamedia.kinocms.service.NewsService;
import avadamedia.kinocms.service.PromotionService;
import avadamedia.kinocms.service.UserService;
import avadamedia.kinocms.service.cinema.CinemaHallService;
import avadamedia.kinocms.service.cinema.CinemaService;
import avadamedia.kinocms.service.common.ImageService;
import avadamedia.kinocms.service.movie.ComingMovieService;
import avadamedia.kinocms.service.movie.CurrentMovieService;
import avadamedia.kinocms.service.banner.MainBannerService;
import avadamedia.kinocms.service.banner.NewsBannerService;
import avadamedia.kinocms.service.page.ContactsPageService;
import avadamedia.kinocms.service.page.MainPageService;
import avadamedia.kinocms.service.page.NewPageService;
import avadamedia.kinocms.service.page.assist.CinemaContactsBlockService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final MainBannerService mainBannerService;
    private final CurrentMovieService currentMovieService;
    private final ComingMovieService comingMovieService;
    private final NewsBannerService newsBannerService;
    private final ImageService imageService;
    private final CinemaService cinemaService;
    private final CinemaHallService cinemaHallService;
    private final PromotionService promotionService;
    private final NewsService newsService;
    private final MainPageService mainPageService;
    private final ContactsPageService contactsPageService;
    private final NewPageService newPageService;
    private final UserService userService;
    private final CinemaContactsBlockService cinemaContactsBlockService;

    @RequestMapping({"/", ""})
    public ModelAndView showHomePage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        mav.addObject("currentMovies", currentMovieService.getAllCurrentMovies());
        mav.addObject("comingMovies", comingMovieService.getAllComingMovies());
        mav.addObject("newsBanners", newsBannerService.getAllNewsBanners());
        mav.addObject("firstNewsBanner", newsBannerService.getFirstId());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        mav.addObject("contactsPage", contactsPageService.getContactsPageById(1L));
        mav.addObject("requiredPages", newPageService.getAllNewPages());
        mav.addObject("newPages", newPageService.getAllNewPages());
        return mav;
    }

    @RequestMapping({"admin/dashboard","admin","admin/"})
    public ModelAndView showAdminPage(){
        ModelAndView mav = new ModelAndView("admin/dashboard");
        if (userService.getAllUsers().iterator().hasNext()) {
            mav.addObject("usersQty", userService.getCountUser());
        } else mav.addObject("usersQty", 0);
        return mav;
    }

    @RequestMapping({"poster","poster/"})
    public ModelAndView showPosterPage(){
        ModelAndView mav = new ModelAndView("public/poster");
        mav.addObject("currentMovies", currentMovieService.getAllCurrentMovies());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"movie/current/{id}"})
    public ModelAndView showCurrentMoviePage(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("public/movie-card");
        mav.addObject("movie", currentMovieService.getCurrentMovieById(id));
        mav.addObject("galleryImages", currentMovieService.getCurrentMovieById(id).getImages());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"movie/coming/{id}"})
    public ModelAndView showComingMoviePage(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("public/movie-card");
        mav.addObject("movie", comingMovieService.getComingMovieById(id));
        mav.addObject("galleryImages", comingMovieService.getComingMovieById(id).getImages());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"schedule","schedule/"})
    public ModelAndView showSchedulePage(){
        ModelAndView mav = new ModelAndView("public/schedule");
        mav.addObject("currentMovies", currentMovieService.getAllCurrentMovies());
        mav.addObject("comingMovies", comingMovieService.getAllComingMovies());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"booking/movie/current/{id}"})
    public ModelAndView showBookingCurrentMoviePage(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("public/booking-card");
        mav.addObject("movie", currentMovieService.getCurrentMovieById(id));
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"booking/movie/coming/{id}"})
    public ModelAndView showBookingComingMoviePage(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("public/booking-card");
        mav.addObject("movie", comingMovieService.getComingMovieById(id));
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"soon","soon/"})
    public ModelAndView showSoonPage(){
        ModelAndView mav = new ModelAndView("public/soon");
        mav.addObject("comingMovies", comingMovieService.getAllComingMovies());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }


    @RequestMapping({"cinemas","cinemas/"})
    public ModelAndView showCinemasPage(){
        ModelAndView mav = new ModelAndView("public/cinemas");
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        mav.addObject("cinemas", cinemaService.getAllCinemas());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"cinemas/{id}"})
    public ModelAndView showCinemaPage(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("public/cinema-card");
        mav.addObject("cinema", cinemaService.getCinemaById(id));
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"hall/{hallId}"})
    public ModelAndView showCinemaHallPage(@PathVariable("hallId") Long hallId){
        ModelAndView mav = new ModelAndView("public/hall-card");
        mav.addObject("cinemaHall", cinemaHallService.getCinemaHallById(hallId));
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"promotions","promotions/"})
    public ModelAndView showPromotionsPage(){
        ModelAndView mav = new ModelAndView("public/promotions");
        mav.addObject("promotions", promotionService.getAllPromotions());
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"promotions/{id}"})
    public ModelAndView showPromotionPage(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("public/promotion-card");
        mav.addObject("promotion", promotionService.getPromotionById(id));
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"news","news/"})
    public ModelAndView showNewsListPage(){
        ModelAndView mav = new ModelAndView("public/news");
        mav.addObject("newsList", newsService.getAllNews());
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"news/{id}"})
    public ModelAndView showNewsPage(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("public/news");
        mav.addObject("news", newsService.getNewsById(id));
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("firstMainBanner", mainBannerService.getFirstId());
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        return mav;
    }

    @RequestMapping({"ad","ad/"})
    public ModelAndView showAdPage(){
        ModelAndView mav = new ModelAndView("public/ad");
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        mav.addObject("adPage", newPageService.getNewPageById(4L));
        return mav;
    }

    @RequestMapping({"cafe","cafe/"})
    public ModelAndView showCafePage(){
        ModelAndView mav = new ModelAndView("public/cafe");
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        mav.addObject("cafePage", newPageService.getNewPageById(2L));
        return mav;
    }

    @RequestMapping({"apps","apps/"})
    public ModelAndView showAppsPage(){
        ModelAndView mav = new ModelAndView("public/apps");
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        mav.addObject("appsPage", newPageService.getNewPageById(2L));
        return mav;
    }

    @RequestMapping({"contacts","contacts/"})
    public ModelAndView showContactsPage(){
        ModelAndView mav = new ModelAndView("public/contacts");
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        mav.addObject("mainBanners", mainBannerService.getAllMainBanners());
        mav.addObject("cinemaContactsBlocks", cinemaContactsBlockService.getAllCinemaContactsBlocks());
        return mav;
    }

}
