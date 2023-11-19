package edu.hw6;

import edu.hw6.task5.HackerNews;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    public void arrayOfMostPopularHackerNewsIdTest() {
        String topStoriesUrl = "https://hacker-news.firebaseio.com/v0/topstories.json";
        String idPrefixUrl = "https://hacker-news.firebaseio.com/v0/item/";

        var hackerNews = new HackerNews(topStoriesUrl, idPrefixUrl);

        long[] ides = hackerNews.hackerNewsTopStories();

        assertTrue(ides.length > 0);
    }

    @Test
    public void arrayOfMostPopularHackerNewsIdTestWrongUrl() {
        String topStoriesUrl = "https://www.youtube.com";
        String idPrefixUrl = "https://hacker-news.firebaseio.com/v0/item/";

        var hackerNews = new HackerNews(topStoriesUrl, idPrefixUrl);

        long[] ides = hackerNews.hackerNewsTopStories();

        assertThat(ides.length).isEqualTo(0);
    }

    @Test
    public void getNewsByIdTest() {
        String topStoriesUrl = "https://hacker-news.firebaseio.com/v0/topstories.json";
        String idPrefixUrl = "https://hacker-news.firebaseio.com/v0/item/";

        var hackerNews = new HackerNews(topStoriesUrl, idPrefixUrl);
        long[] ides = hackerNews.hackerNewsTopStories();
        assertTrue(ides.length > 0);

        String news = hackerNews.news(ides[0]);

        assertNotNull(news);
    }

    @Test
    public void getNewsByIdWrongUrlIdTest() {
        String topStoriesUrl = "https://hacker-news.firebaseio.com/v0/topstories.json";
        String idPrefixUrl = "https://www.youtube.com";

        var hackerNews = new HackerNews(topStoriesUrl, idPrefixUrl);
        long[] ides = hackerNews.hackerNewsTopStories();
        assertTrue(ides.length > 0);

        String news = hackerNews.news(ides[0]);

        assertNull(news);
    }
}
