package avadamedia.kinocms.model.common;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import avadamedia.kinocms.model.movies.ComingMovie;
import avadamedia.kinocms.model.movies.CurrentMovie;
import avadamedia.kinocms.model.news.News;
import avadamedia.kinocms.model.pages.ContactsPage;
import avadamedia.kinocms.model.pages.MainPage;
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
@Table(name = "seo")
public class SEO extends MappedEntity {

    @Column
    private String url;
    @Column
    private String title;
    @Column
    private String keywords;
    @Column
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_movie_id")
    private CurrentMovie currentMovie;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coming_movie_id")
    private ComingMovie comingMovie;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_hall_id")
    private CinemaHall cinemaHall;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contacts_page_id")
    private ContactsPage contactsPage;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_page_id")
    private NewPage newPage;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_page_id")
    private MainPage mainPage;

}
