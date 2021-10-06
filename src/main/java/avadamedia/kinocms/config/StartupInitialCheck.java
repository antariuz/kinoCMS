package avadamedia.kinocms.config;

import avadamedia.kinocms.model.banners.BackgroundBanner;
import avadamedia.kinocms.model.movies.assist.MovieType;
import avadamedia.kinocms.service.BackgroundBannerService;
import avadamedia.kinocms.service.MovieTypeService;
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

    @EventListener(ApplicationReadyEvent.class)
    public void BackgroundBannerEntityInit() {
        if (backgroundBannerService.getMaxId() == null) {
            backgroundBannerService.createBackgroundBanner(new BackgroundBanner());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void MovieTypeInit() {
        if (movieTypeService.getMaxId() == null) {
            movieTypeService.createMovieType(new MovieType("2D"));
            movieTypeService.createMovieType(new MovieType("3D"));
            movieTypeService.createMovieType(new MovieType("IMAX"));
        }
    }

}
