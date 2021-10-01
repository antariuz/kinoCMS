package avadamedia.kinocms.model.cinemas.assist;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "diagram_image")
    private String diagramImage;
    @Column(name = "top_banner")
    private String topBanner;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "cinemaHall")
    private List<Image> images;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "cinemaHall")
    private SEO SEOBlock;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

}
