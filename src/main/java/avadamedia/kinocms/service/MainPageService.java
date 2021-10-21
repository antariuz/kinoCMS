package avadamedia.kinocms.service;

import avadamedia.kinocms.model.pages.MainPage;

public interface MainPageService {

    void createMainPage(MainPage mainPage);

    void updateMainPage(MainPage mainPage);

    void deleteMainPageById(Long id);

    Iterable<MainPage> getAllMainPages();

    MainPage getMainPageById(Long id);

    Long getMaxId();

}
