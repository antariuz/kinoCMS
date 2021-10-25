package avadamedia.kinocms.service.impl.pages;

import avadamedia.kinocms.model.pages.MainPage;
import avadamedia.kinocms.repository.page.MainPageRepository;
import avadamedia.kinocms.service.page.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainPageServiceImpl implements MainPageService {

    private final MainPageRepository repository;

    @Override
    public void createMainPage(MainPage mainPage) {
        repository.save(mainPage);
    }

    @Override
    public void updateMainPage(MainPage mainPage) {
        repository.save(mainPage);
    }

    @Override
    public void deleteMainPageById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<MainPage> getAllMainPages() {
        return repository.findAll();
    }

    @Override
    public MainPage getMainPageById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Long getLastId() {
        return repository.getLastId();
    }

}
