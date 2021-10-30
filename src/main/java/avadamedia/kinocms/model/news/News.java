package avadamedia.kinocms.model.news;

import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.MappedEntity;
import avadamedia.kinocms.model.common.SEO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News extends MappedEntity {

    @Column
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column
    private Date publishDate;
    @Column
    private boolean status;
    @Lob
    @Column
    private String description;
    @Column(name = "main_image")
    private String mainImage;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="news_id", referencedColumnName="id")
    private List<Image> images = new ArrayList<>();

    @Column
    private String trailerUrl;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seo_id")
    private SEO seo;

    @Transient
    public String getImagePath() {
        if (mainImage == null || getId() == null) return null;
        return "/uploaded-images/news/" + getId() + "/" + mainImage;
    }

}
