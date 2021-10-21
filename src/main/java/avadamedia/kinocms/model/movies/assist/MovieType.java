package avadamedia.kinocms.model.movies.assist;

import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.movies.CurrentMovie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie_type")
public class MovieType extends MappedEntity {

    @Column
    private String name;

}
