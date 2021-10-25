package avadamedia.kinocms.service.impl.pages;

import avadamedia.kinocms.model.pages.NewPage;
import avadamedia.kinocms.repository.page.NewPageRepository;
import avadamedia.kinocms.service.page.NewPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NewPageServiceImpl implements NewPageService {

    private final NewPageRepository repository;

    @Override
    public void createNewPage(NewPage newPage) {
        repository.save(newPage);
    }

    @Override
    public void updateNewPage(NewPage newPage) {
        repository.save(newPage);
    }

    @Override
    public void deleteNewPageById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<NewPage> getAllNewPages() {
        return repository.findAll();
    }

    @Override
    public NewPage getNewPageById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Long getLastId() {
        return repository.getLastId();
    }

}
