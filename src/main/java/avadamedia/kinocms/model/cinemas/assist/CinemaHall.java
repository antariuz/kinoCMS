package avadamedia.kinocms.model.cinemas.assist;

import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cinema_hall")
public class CinemaHall extends MappedEntity {

    @Column
    private Integer number;
    @Lob
    @Column
    private String description;
    @Column(name = "main_image")
    private String mainImage;
    @Column(name = "top_banner")
    private String topBanner;
    @Column(name = "cinema_id")
    private Long cinemaId;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "cinema_hall_id", referencedColumnName = "id")
    private List<Image> images = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seo_id")
    private SEO seo;

    @Transient
    public String getImagePath() {
        if (mainImage == null || getId() == null) return null;
        return "/uploaded-images/cinema-halls/" + getId() + "/" + mainImage;
    }

    @Transient
    public String getTopBannerPath() {
        if (topBanner == null || getId() == null) return null;
        return "/uploaded-images/cinema-halls/" + getId() + "/" + topBanner;
    }

}
