package avadamedia.kinocms.model.pages.assist;

import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.pages.ContactsPage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cinema_contacts_block")
public class CinemaContactsBlock extends MappedEntity {

    @Column
    private String name;
    @Column
    private String address;
    @Column(name = "map_coordinates")
    private String mapCoordinates;
    @Column(name = "logo")
    private String logo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contacts_page_id")
    private ContactsPage contactsPage;

}
