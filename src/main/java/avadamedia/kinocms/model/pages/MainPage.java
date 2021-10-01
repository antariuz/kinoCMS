package avadamedia.kinocms.model.pages;

import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "main_page")
public class MainPage extends MappedEntity {

    @Column
    private String firstPhoneNumber;
    @Column
    private String secondPhoneNumber;
    @Column
    private boolean status;
    @Column
    private String seoText;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "mainPage")
    private SEO SEOBlock;

}
