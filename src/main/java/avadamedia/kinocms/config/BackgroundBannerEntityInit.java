package avadamedia.kinocms.config;

import avadamedia.kinocms.model.banners.BackgroundBanner;
import avadamedia.kinocms.service.BackgroundBannerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BackgroundBannerEntityInit {

    private final BackgroundBannerService backgroundBannerService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        if (backgroundBannerService.getMaxId() == null) {
            backgroundBannerService.createBackgroundBanner(new BackgroundBanner());
        }
    }
}
