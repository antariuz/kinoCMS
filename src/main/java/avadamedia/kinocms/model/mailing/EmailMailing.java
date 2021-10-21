package avadamedia.kinocms.model.mailing;

import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.mailing.assist.Template;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany
    @JoinColumn(name = "template_list_id")
    private List<Template> template;

}
