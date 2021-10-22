package avadamedia.kinocms.model.cinemas.assist;

import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @JoinColumn(name="image_list_id", referencedColumnName="id")
    private List<Image> imageList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seo_id")
    private SEO seo;

}
