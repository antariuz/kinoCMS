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

    @Column(name = "first_phone_number")
    private String firstPhoneNumber;
    @Column(name = "second_phone_number")
    private String secondPhoneNumber;
    @Column
    private boolean status;
    @Column
    private String seoText;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seo_id")
    private SEO seo;

}
