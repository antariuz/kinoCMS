package avadamedia.kinocms.service.impl.pages.assist;

import avadamedia.kinocms.model.pages.assist.CinemaContactsBlock;
import avadamedia.kinocms.repository.CinemaContactsBlockRepository;
import avadamedia.kinocms.service.page.assist.CinemaContactsBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CinemaContactsBlockServiceImpl implements CinemaContactsBlockService {

    private final CinemaContactsBlockRepository repository;

    @Override
    public void createCinemaContactsBlock(CinemaContactsBlock cinemaContactsBlock) {
        repository.save(cinemaContactsBlock);
    }

    @Override
    public void updateCinemaContactsBlock(CinemaContactsBlock cinemaContactsBlock) {
        repository.save(cinemaContactsBlock);
    }

    @Override
    public void deleteCinemaContactsBlockById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<CinemaContactsBlock> getAllCinemaContactsBlocks() {
        return repository.findAll();
    }

    @Override
    public CinemaContactsBlock getCinemaContactsBlockById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Long getLastId() {
        return repository.getLastId();
    }

}
