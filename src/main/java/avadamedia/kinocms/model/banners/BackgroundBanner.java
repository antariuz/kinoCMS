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
@Table(name = "background_banner")
public class BackgroundBanner extends MappedEntity {

    @Column(name = "image_url")
    private String imageUrl;

}
