package avadamedia.kinocms.service;

import avadamedia.kinocms.model.pages.ContactsPage;

public interface ContactsPageService {

    void createContactsPage(ContactsPage contactsPage);

    void updateContactsPage(ContactsPage contactsPage);

    void deleteContactsPageById(Long id);

    Iterable<ContactsPage> getAllContactsPages();

    ContactsPage getContactsPageById(Long id);

    Long getMaxId();

}
