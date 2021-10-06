package avadamedia.kinocms.model.movies.assist;

import avadamedia.kinocms.model.common.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie_type")
public class MovieType extends MappedEntity {

    @Column
    private String type;

}
