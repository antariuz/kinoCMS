package avadamedia.kinocms.model.pages;

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
@Table(name = "new_page")
public class NewPage extends MappedEntity {

    @Column
    private String name;
    @Column
    private boolean status;
    @Column
    private String description;
    @Column(name = "main_image")
    private String mainImage;

    @OneToMany
    @JoinColumn(name = "image_list_id")
    private List<Image> imageList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private SEO seo;

}
