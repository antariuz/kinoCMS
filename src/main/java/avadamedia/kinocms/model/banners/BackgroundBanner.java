package avadamedia.kinocms.model.banners;

import avadamedia.kinocms.model.common.MappedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "background_banner")
public class BackgroundBanner extends MappedEntity {

    @Column(name = "main_image")
    private String mainImage;

    @Transient
    public String getImagePath() {
        if (mainImage == null || getId() == null) return null;
        return "/uploaded-images/background-banners/" + mainImage;
    }

}
