package avadamedia.kinocms.model.mailing;

import avadamedia.kinocms.model.common.MappedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sms_mailing")
public class SMSMailing extends MappedEntity {

    @Column
    private String text;
    @Column
    private Long smsQuantity;

}
