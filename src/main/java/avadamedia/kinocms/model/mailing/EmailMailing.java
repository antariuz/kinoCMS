package avadamedia.kinocms.model.mailing;

import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.mailing.assist.Template;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "email_mailing")
public class EmailMailing extends MappedEntity {

    @Column
    private String text;
    @Column
    private Long emailQuantity;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="email_mailing_id", referencedColumnName="id")
    private List<Template> template = new ArrayList<>();

}
