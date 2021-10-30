package avadamedia.kinocms.config;

import avadamedia.kinocms.model.banners.BackgroundBanner;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.movies.assist.MovieType;
import avadamedia.kinocms.model.pages.ContactsPage;
import avadamedia.kinocms.model.pages.MainPage;
import avadamedia.kinocms.model.pages.NewPage;
import avadamedia.kinocms.service.common.ImageService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StartupInitialCheck {

    private final BackgroundBannerService backgroundBannerService;
    private final MovieTypeService movieTypeService;
    private final MainPageService mainPageService;
    private final NewPageService newPageService;
    private final ContactsPageService contactsPageService;
    private final ImageService imageService;

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
            MainPage mainPage = new MainPage();
            mainPage.setSeo(new SEO());
            mainPageService.createMainPage(mainPage);
        }
        if (contactsPageService.getLastId() == null) {
            ContactsPage contactsPage = new ContactsPage();
            contactsPage.setSeo(new SEO());
            contactsPageService.createContactsPage(contactsPage);
        }
        if (newPageService.getLastId() == null) {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "О кинотеатре",
                    "Кафе-Бар",
                    "VIP-зал",
                    "Реклама",
                    "Мобильные приложения"));
            for (int i = 0; i < 5; i++) {
                NewPage newPage = new NewPage();
                newPage.setName(list.get(i));
                newPage.setSeo(new SEO());
                newPage.setImages(imageService.initImageList(4));
                newPageService.createNewPage(newPage);
            }
        }
    }

}
