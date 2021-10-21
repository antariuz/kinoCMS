package avadamedia.kinocms.model.mailing.assist;

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
@Table(name = "templates")
public class Template extends MappedEntity {

    @Column
    private String name;

}
