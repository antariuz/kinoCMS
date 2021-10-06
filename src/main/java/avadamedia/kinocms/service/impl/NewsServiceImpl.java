package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.news.News;
import avadamedia.kinocms.repository.NewsRepository;
import avadamedia.kinocms.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NewsServiceImpl implements NewsService {

    private final NewsRepository repository;

    @Override
    public void createNews(News news) {
        repository.save(news);
    }

    @Override
    public void updateNews(News news) {
        repository.save(news);
    }

    @Override
    public void deleteNewsById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<News> getAllNews() {
        return repository.findAll();
    }

    @Override
    public News getNewsById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
