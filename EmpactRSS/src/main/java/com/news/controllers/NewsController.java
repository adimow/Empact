package com.news.controllers.rest;


import com.news.models.NewsItem;
import com.news.services.NewsServices;
import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    @Autowired
    private NewsServices newsServices;


    @GetMapping("/news")
    public List<NewsItem> getNewsList(@RequestParam(name = "sort", required = false) String sortParam,
                                      @RequestParam(name = "keyword", required = false) String searchParam) throws Exception {
        List<NewsItem> newsList =newsServices.getNewsList(sortParam, searchParam);
        return newsList;
    }

    @GetMapping("/news/save")
    public String save() {
        List<NewsItem> news = newsServices.parseRssFeed();
        newsServices.save(news);
        return "News saved successfully!";
    }

}


