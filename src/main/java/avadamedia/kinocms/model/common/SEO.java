package avadamedia.kinocms.model.common;

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
@Table(name = "seo")
public class SEO extends MappedEntity {

    @Column
    private String url;
    @Column
    private String title;
    @Column
    private String keywords;
    @Column(name = "seo_description")
    private String seoDescription;

}
