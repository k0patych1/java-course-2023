package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private final String baseUrl;

    private final String idPrefixUrl;

    public HackerNews(String baseUrl, String idPrefixUrl) {
        this.baseUrl = baseUrl;
        this.idPrefixUrl = idPrefixUrl;
    }

    public long[] hackerNewsTopStories() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String[] idsStr = response.body().replace("[", "").replace("]", "").split(",");
            int idCount = idsStr.length;
            long[] ids = new long[idCount];
            for (int i = 0; i < idCount; ++i) {
                ids[i] = Long.parseLong(idsStr[i]);
            }
            return ids;
        } catch (IOException | NumberFormatException | InterruptedException e) {
            return new long[0];
        }
    }

    public String news(long id) {
        String idUrl = idPrefixUrl + id + ".json";

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(idUrl))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Pattern pattern = Pattern.compile("\"title\":\"([^\"]*)\"");
            Matcher matcher = pattern.matcher(response.body());
            if (matcher.find()) {
                return matcher.group(1);
            }

            return null;

        } catch (IOException | NumberFormatException | InterruptedException e) {
            return null;
        }
    }
}
