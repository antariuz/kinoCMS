package avadamedia.kinocms.model.movies;

import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.movies.assist.MovieInfo;
import avadamedia.kinocms.model.movies.assist.MovieType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "coming_movies")
public class ComingMovie extends MappedEntity {

    @Column
    private String name;
    @Column
    private String description;
    @Column(name = "main_image")
    private String mainImage;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "comingMovie")
    private List<Image> images;
    @Column
    private String trailerUrl;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<MovieType> movieType;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "comingMovie")
    private SEO SEOBlock;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "comingMovie")
    private MovieInfo movieInfo;

    @Transient
    public String getImagePath() {
        if (mainImage == null || getId() == null) return null;
        return "/uploaded-images/coming-movies/" + getId() + "/" + mainImage;
    }

}
