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
@Table(name = "main_banner")
public class MainBanner extends MappedEntity {

    @Column(name = "main_image")
    private String mainImage;
    @Column(name = "url")
    private String url;
    @Column(name = "text")
    private String text;

    @Transient
    public String getImagePath() {
        if (mainImage == null || getId() == null) return null;
        return "/uploaded-images/main-banners/" + getId() + "/" + mainImage;
    }

}
