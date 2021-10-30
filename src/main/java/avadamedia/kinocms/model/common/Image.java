package avadamedia.kinocms.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image extends MappedEntity {

    @Column
    private String name;

    @Transient
    public String getImagePath() {
        if (name == null || getId() == null) return null;
        return "/uploaded-images/gallery-images/" + name;
    }

}
