package avadamedia.kinocms.service;

import avadamedia.kinocms.model.news.News;

public interface NewsService {

    void createNews(News news);

    void updateNews(News news);

    void deleteNewsById(Long id);

    Iterable<News> getAllNews();

    News getNewsById(Long id);

}
