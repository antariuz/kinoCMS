package avadamedia.kinocms.service;

import avadamedia.kinocms.model.mailing.EmailMailing;

public interface EmailMailingService {

    void createEmailMailing(EmailMailing emailMailing);

    void updateEmailMailing(EmailMailing emailMailing);

    void deleteEmailMailingById(Long id);

    Iterable<EmailMailing> getAllEmailMailings();

    EmailMailing getEmailMailingById(Long id);

}
