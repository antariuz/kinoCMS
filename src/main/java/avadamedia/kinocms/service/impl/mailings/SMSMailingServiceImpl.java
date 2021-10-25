package avadamedia.kinocms.service.impl.mailings;

import avadamedia.kinocms.model.mailing.SMSMailing;
import avadamedia.kinocms.repository.mailings.SMSMailingRepository;
import avadamedia.kinocms.service.mailing.SMSMailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SMSMailingServiceImpl implements SMSMailingService {

    private final SMSMailingRepository repository;

    @Override
    public void createSMSMailing(SMSMailing smsMailing) {
        repository.save(smsMailing);
    }

    @Override
    public void updateSMSMailing(SMSMailing smsMailing) {
        repository.save(smsMailing);
    }

    @Override
    public void deleteSMSMailingById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<SMSMailing> getAllSMSMailings() {
        return repository.findAll();
    }

    @Override
    public SMSMailing getSMSMailingById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
