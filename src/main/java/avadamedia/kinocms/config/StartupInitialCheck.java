package avadamedia.kinocms.config;

import avadamedia.kinocms.model.banners.BackgroundBanner;
import avadamedia.kinocms.model.movies.assist.MovieType;
import avadamedia.kinocms.model.pages.ContactsPage;
import avadamedia.kinocms.model.pages.MainPage;
import avadamedia.kinocms.service.movie.MovieTypeService;
import avadamedia.kinocms.service.banner.BackgroundBannerService;
import avadamedia.kinocms.service.page.ContactsPageService;
import avadamedia.kinocms.service.page.MainPageService;
import avadamedia.kinocms.service.page.NewPageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StartupInitialCheck {

    private final BackgroundBannerService backgroundBannerService;
    private final MovieTypeService movieTypeService;
    private final MainPageService mainPageService;
    private final NewPageService newPageService;
    private final ContactsPageService contactsPageService;

    @EventListener(ApplicationReadyEvent.class)
    public void backgroundBannerEntityInit() {
        if (backgroundBannerService.getLastId() == null) {
            backgroundBannerService.createBackgroundBanner(new BackgroundBanner());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void movieTypeInit() {
        if (movieTypeService.getLastId() == null) {
            movieTypeService.createMovieType(new MovieType("2D"));
            movieTypeService.createMovieType(new MovieType("3D"));
            movieTypeService.createMovieType(new MovieType("IMAX"));
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void requiredPagesInit() {
        if (mainPageService.getLastId() == null) {
            mainPageService.createMainPage(new MainPage());
        }
        if (contactsPageService.getLastId() == null) {
            contactsPageService.createContactsPage(new ContactsPage());
        }
    }

}
