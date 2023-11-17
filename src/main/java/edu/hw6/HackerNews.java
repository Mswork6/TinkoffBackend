package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL_FORMAT = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    private final HttpClient httpClient;

    public HackerNews() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public long[] hackerNewsTopStories(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();
                return parseTopStoriesJson(json);
            }
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }

        return new long[0];
    }

    public long[] hackerNewsTopStories() {
        return hackerNewsTopStories(TOP_STORIES_URL);
    }

    public String news(long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(ITEM_URL_FORMAT, id)))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();
                return parseNewsTitleJson(json);
            }
        } catch (IOException | InterruptedException e) {
            return "";
        }

        return "";
    }

    private long[] parseTopStoriesJson(String json) {
        String[] idStrings = json.replaceAll("[\\[\\]]", "").split(",");
        long[] ids = new long[idStrings.length];
        for (int i = 0; i < idStrings.length; i++) {
            ids[i] = Long.parseLong(idStrings[i]);
        }
        return ids;
    }

    private String parseNewsTitleJson(String json) {
        String titlePattern = "\"title\":\"(.*?)\",\"type\"";
        Pattern pattern = Pattern.compile(titlePattern);
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        HackerNews hackerNews = new HackerNews();

        long[] topStories = hackerNews.hackerNewsTopStories();
        System.out.println(Arrays.toString(topStories));

        String newsTitle = hackerNews.news(topStories[1]);
        System.out.println(newsTitle);
    }
}
