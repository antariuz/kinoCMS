package avadamedia.kinocms.service;

import avadamedia.kinocms.model.mailing.SMSMailing;

public interface SMSMailingService {

    void createSMSMailing(SMSMailing smsMailing);

    void updateSMSMailing(SMSMailing smsMailing);

    void deleteSMSMailingById(Long id);

    Iterable<SMSMailing> getAllSMSMailings();

    SMSMailing getSMSMailingById(Long id);

}
