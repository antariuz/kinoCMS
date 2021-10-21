package avadamedia.kinocms.model.movies.assist;

import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.movies.ComingMovie;
import avadamedia.kinocms.model.movies.CurrentMovie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie_info")
public class MovieInfo extends MappedEntity {

    @Column
    private String year;
    @Column
    private String country;
    @Column
    private String compositor;
    @Column
    private String producer;
    @Column
    private String director;
    @Column
    private String operator;
    @Column
    private String screenwriter;
    @Column
    private String genre;
    @Column
    private String budget;
    @Column(name = "age_restriction")
    private String ageRestriction;
    @Column
    private String time;

}
