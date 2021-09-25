package avadamedia.kinocms.model;

import avadamedia.kinocms.model.assist.MovieType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie extends MappedEntity {

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String mainImageUrl;
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "imageList_id")
//    private List<String> imageList;
    @Column
    private String trailerUrl;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "movie_type_set_id")
//    private Set<MovieType> movieTypeSet;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "seoBlock_id")
//    private SeoBlock seoBlock;

}
