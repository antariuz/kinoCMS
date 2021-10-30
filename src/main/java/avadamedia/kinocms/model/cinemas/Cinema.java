package avadamedia.kinocms.model.cinemas;

import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
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
@Table(name = "cinema")
public class Cinema extends MappedEntity {

    @Column
    private String name;
    @Lob
    @Column
    private String description;
    @Lob
    @Column
    private String conditions;
    @Column(name = "main_image")
    private String mainImage;
    @Column(name = "top_banner")
    private String topBanner;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    private List<Image> images = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="cinema_id", referencedColumnName="id")
    private List<CinemaHall> cinemaHalls = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seo_id")
    private SEO seo;

    @Transient
    public String getImagePath() {
        if (mainImage == null || getId() == null) return null;
        return "/uploaded-images/cinemas/" + getId() + "/" + mainImage;
    }

    @Transient
    public String getTopBannerPath() {
        if (topBanner == null || getId() == null) return null;
        return "/uploaded-images/cinemas/" + getId() + "/" + topBanner;
    }

}
