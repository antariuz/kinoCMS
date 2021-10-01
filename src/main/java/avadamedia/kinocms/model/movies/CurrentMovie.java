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
@Table(name = "current_movies")
public class CurrentMovie extends MappedEntity {

    @Column
    private String name;
    @Column
    private String description;
    @Column(name = "image_name")
    private String imageName;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "currentMovie")
    private List<Image> images;
    @Column
    private String trailerUrl;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<MovieType> movieType;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "currentMovie")
    private SEO SEOBlock;
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL, mappedBy = "currentMovie")
    private MovieInfo movieInfo;

    @Transient
    public String getImagePath() {
        if (imageName == null || getId() == null) return null;
        return "/uploaded-images/current-movies/" + getId() + "/" + imageName;
    }

}
