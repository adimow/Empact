package com.news.services;

import com.news.dao.NewsItemRepository;
import com.news.models.NewsItem;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Data
@Service
public class NewsServices {

    private final String RSS_FEED_URL = "https://rss.nytimes.com/services/xml/rss/nyt/World.xml";
    @Autowired
    private NewsItemRepository newsItemRepository;

    public List<NewsItem> getNewsList(String sort, String keyword) {
        List<NewsItem> newsList = parseRssFeed();
        if (sort != null && !sort.isEmpty()) {
            if (sort.equalsIgnoreCase("date_asc")) {
                newsList.sort(Comparator.comparing(NewsItem::getPubDate));
            } else if (sort.equalsIgnoreCase("date_desc")) {
                newsList.sort(Comparator.comparing(NewsItem::getPubDate).reversed());
            } else if (sort.equalsIgnoreCase("title_asc")) {
                newsList.sort(Comparator.comparing(NewsItem::getTitle));
            } else if (sort.equalsIgnoreCase("title_desc")) {
                newsList.sort(Comparator.comparing(NewsItem::getTitle).reversed());
            }
        }
        if (keyword != null && !keyword.isEmpty()) {
            newsList = newsList.stream().filter(news -> news.getTitle().contains(keyword)).collect(Collectors.toList());
        }
        return newsList;
    }

    public void save(List<NewsItem> news) {
        news = parseRssFeed();
        newsItemRepository.saveAll(news);
    }

    public List<NewsItem> parseRssFeed() {
        List<NewsItem> newsList = new ArrayList<>();
        try {
            URL feedUrl = new URL(RSS_FEED_URL);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            for (SyndEntry entry : feed.getEntries()) {
                NewsItem news = new NewsItem();
                news.setTitle(entry.getTitle());
                news.setLink(entry.getLink());
                news.setDescription(entry.getDescription().getValue());
                news.setPubDate(Date.from(entry.getPublishedDate().toInstant()));
                newsList.add(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }


}



