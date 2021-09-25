package avadamedia.kinocms.model.assist;

import avadamedia.kinocms.model.MappedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie_type")
public class MovieType extends MappedEntity {

    @Column(name = "3d")
    private boolean threeD;
    @Column(name = "2d")
    private boolean twoD;
    @Column(name = "imax")
    private boolean IMax;

}
