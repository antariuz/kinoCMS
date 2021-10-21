package avadamedia.kinocms.model.pages;

import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.pages.assist.CinemaContactsBlock;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contacts_page")
public class ContactsPage extends MappedEntity {

    @OneToMany
    @JoinColumn(name = "cinema_contacts_block_list_id")
    private List<CinemaContactsBlock> cinemaContactsBlocks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    private SEO seo;

}
