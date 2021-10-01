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
@Table(name = "news_banners")
public class NewsBanner extends MappedEntity {

    @Column(name = "image_name")
    private String imageName;
    @Column(name = "url")
    private String url;

    @Transient
    public String getImagePath() {
        if (imageName == null || getId() == null) return null;
        return "/uploaded-images/news-banners/" + getId() + "/" + imageName;
    }

}
