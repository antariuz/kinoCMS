package avadamedia.kinocms.model.banners;

import avadamedia.kinocms.model.MappedEntity;
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
@Table(name = "main_banners")
public class MainBanner extends MappedEntity {

    @Column
    private String imageUrl;
    @Column
    private String url;
    @Column
    private String text;

}
