package avadamedia.kinocms.model.pages;

import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.pages.assist.CinemaContactsBlock;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contacts_page")
public class ContactsPage extends MappedEntity {

    @Column
    private boolean status;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "contacts_page_id", referencedColumnName = "id")
    private List<CinemaContactsBlock> cinemaContactsBlocks = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seo_id")
    private SEO seo;

}
