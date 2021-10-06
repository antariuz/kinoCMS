package avadamedia.kinocms.repository.mailings;

import avadamedia.kinocms.model.mailing.SMSMailing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SMSMailingRepository extends CrudRepository<SMSMailing, Long> {
}
