package avadamedia.kinocms.model.cinemas.assist;

import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cinema_halls")
public class CinemaHall extends MappedEntity {

    @Column
    private Integer number;
    @Column
    private String description;
    @Column(name = "main_image")
    private String mainImage;
    @Column(name = "top_banner")
    private String topBanner;

    @OneToMany
    @JoinColumn(name = "image_list_id")
    @ToString.Exclude
    private List<Image> imageList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private SEO seo;

}
