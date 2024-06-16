package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.bookstore.entity.News;
import project.bookstore.exception.NewsNotFoundException;
import project.bookstore.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    public Page<News> getPaginatedNews(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }
    public News save(News news) {
        return newsRepository.save(news);
    }

    public List<News> listAll() {
        return newsRepository.findAll();
    }

    public News get(Integer id) throws NewsNotFoundException {
        Optional<News> result = newsRepository.findById(id);

        return result.orElseThrow(() -> new NewsNotFoundException("News not found!"));
    }

    public void delete(Integer id) throws NewsNotFoundException {
        Long count = newsRepository.countById(id);

        if (count == null || count == 0) {
            throw new NewsNotFoundException("Could not find any news with ID " + id);
        }

        newsRepository.deleteById(id);
    }

}
