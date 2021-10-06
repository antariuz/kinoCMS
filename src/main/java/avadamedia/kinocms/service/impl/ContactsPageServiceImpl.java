package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.pages.ContactsPage;
import avadamedia.kinocms.repository.pages.ContactsPageRepository;
import avadamedia.kinocms.service.ContactsPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContactsPageServiceImpl implements ContactsPageService {

    private final ContactsPageRepository repository;

    @Override
    public void createContactsPage(ContactsPage contactsPage) {
        repository.save(contactsPage);
    }

    @Override
    public void updateContactsPage(ContactsPage contactsPage) {
        repository.save(contactsPage);
    }

    @Override
    public void deleteContactsPageById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<ContactsPage> getAllContactsPages() {
        return repository.findAll();
    }

    @Override
    public ContactsPage getContactsPageById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
