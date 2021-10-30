package avadamedia.kinocms.service.page.assist;

import avadamedia.kinocms.model.pages.assist.CinemaContactsBlock;

public interface CinemaContactsBlockService {

    void createCinemaContactsBlock(CinemaContactsBlock cinemaContactsBlock);

    void updateCinemaContactsBlock(CinemaContactsBlock cinemaContactsBlock);

    void deleteCinemaContactsBlockById(Long id);

    Iterable<CinemaContactsBlock> getAllCinemaContactsBlocks();

    CinemaContactsBlock getCinemaContactsBlockById(Long id);

    Long getLastId();

}
