package avadamedia.kinocms.model.pages.assist;

import avadamedia.kinocms.model.common.MappedEntity;
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
@Entity
@Table(name = "cinema_contacts_block")
public class CinemaContactsBlock extends MappedEntity {

    @Column
    private String name;
    @Column
    private boolean status;
    @Column
    private String address;
    @Column(name = "map_coordinates")
    private String mapCoordinates;
    @Column(name = "main_image")
    private String mainImage;
    @Column(name = "contacts_page_id")
    private Long contactsPageId;


    @Transient
    public String getImagePath() {
        if (mainImage == null || getId() == null) return null;
        return "/uploaded-images/contacts-pages/cinemaContactsBlocks/" + getId() + "/" + mainImage;
    }

}
