package avadamedia.kinocms.model.cinemas;

import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cinemas")
public class Cinema extends MappedEntity {

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String conditions;
    @Column(name = "main_image")
    private String mainImage;
    @Column(name = "top_banner")
    private String topBanner;

    @OneToMany
    @JoinColumn(name="image_list_id", referencedColumnName="id")
    private List<Image> imageList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="cinema_hall_list_id", referencedColumnName="id")
    private List<CinemaHall> cinemaHallList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seo_id")
    private SEO seo;

    @Transient
    public String getImagePath() {
        if (mainImage == null || getId() == null) return null;
        return "/uploaded-images/cinemas/" + getId() + "/" + mainImage;
    }

}
