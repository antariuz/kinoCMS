package avadamedia.kinocms.repository.mailings;

import avadamedia.kinocms.model.mailing.EmailMailing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailMailingRepository extends CrudRepository<EmailMailing, Long> {
}
