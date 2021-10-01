package avadamedia.kinocms.model.common;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
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
@Table(name = "images")
public class Image extends MappedEntity {

    @Column
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_movie_id")
    private CurrentMovie currentMovie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coming_movie_id")
    private ComingMovie comingMovie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_hall_id")
    private CinemaHall cinemaHall;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_page_id")
    private NewPage newPage;

}
