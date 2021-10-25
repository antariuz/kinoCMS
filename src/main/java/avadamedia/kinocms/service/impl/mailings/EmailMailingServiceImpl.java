package avadamedia.kinocms.service.impl.mailings;

import avadamedia.kinocms.model.mailing.EmailMailing;
import avadamedia.kinocms.repository.mailings.EmailMailingRepository;
import avadamedia.kinocms.service.mailing.EmailMailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailMailingServiceImpl implements EmailMailingService {

    private final EmailMailingRepository repository;

    @Override
    public void createEmailMailing(EmailMailing emailMailing) {
        repository.save(emailMailing);
    }

    @Override
    public void updateEmailMailing(EmailMailing emailMailing) {
        repository.save(emailMailing);
    }

    @Override
    public void deleteEmailMailingById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<EmailMailing> getAllEmailMailings() {
        return repository.findAll();
    }

    @Override
    public EmailMailing getEmailMailingById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
