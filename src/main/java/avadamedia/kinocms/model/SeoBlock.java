package avadamedia.kinocms.model;

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
@Table(name = "seoblocks")
public class SeoBlock extends MappedEntity {

    @Column
    private String url;
    @Column
    private String title;
    @Column
    private String keywords;
    @Column
    private String description;

}
