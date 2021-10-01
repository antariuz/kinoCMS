package avadamedia.kinocms.model.common;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import avadamedia.kinocms.model.mailing.EmailMailing;
import avadamedia.kinocms.model.movies.ComingMovie;
import avadamedia.kinocms.model.movies.CurrentMovie;
import avadamedia.kinocms.model.news.News;
import avadamedia.kinocms.model.pages.NewPage;
import avadamedia.kinocms.model.promotions.Promotion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "templates")
public class Template extends MappedEntity {

    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_mailing_id")
    private EmailMailing emailMailing;

}
