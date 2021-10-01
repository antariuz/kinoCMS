package avadamedia.kinocms.model.cinemas;

import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
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
@Table(name = "cinemas")
public class Cinema extends MappedEntity {

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String condition;
    @Column
    private String logo;
    @Column(name = "top_banner")
    private String topBanner;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "cinema")
    private List<Image> images;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "cinema")
    private List<CinemaHall> cinemaHall;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "cinema")
    private SEO SEOBlock;

}
