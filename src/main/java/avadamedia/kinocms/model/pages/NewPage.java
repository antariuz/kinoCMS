package avadamedia.kinocms.model.pages;

import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "new_page")
public class NewPage extends MappedEntity {

    @Column
    private String name;
    @Column
    private boolean status;
    @Lob
    @Column
    private String description;
    @Column(name = "main_image")
    private String mainImage;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="new_page_id", referencedColumnName="id")
    private List<Image> images = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seo_id")
    private SEO seo;

    @Transient
    public String getImagePath() {
        if (mainImage == null || getId() == null) return null;
        return "/uploaded-images/new-pages/" + getId() + "/" + mainImage;
    }

}
