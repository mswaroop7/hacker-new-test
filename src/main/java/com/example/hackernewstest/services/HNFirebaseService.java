package com.example.hackernewstest.services;

import com.example.hackernewstest.beststories.models.HNStory;
import com.example.hackernewstest.comments.models.HNComment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.hackernewstest.constants.HNApiConstants.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class HNFirebaseService {

    public List<HNStory> fetchBestStories() {
        List<HNStory> bestStories = new ArrayList<>();
        List<String> bestStoriesIds = fetchBestStoriesIds();
        for (String storyId : bestStoriesIds) {
            bestStories.add(fetchStory(storyId));
        }
        return bestStories;
    }

    private List<String> fetchBestStoriesIds() {
        String url = HN_FB_BASE_URL + HN_FB_P_BEST_STORIES + HN_FB_LIMIT_PARAMS;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<String>> bestStoriesResponse = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );
        return bestStoriesResponse.getBody();
    }

    public HNStory fetchStory(String storyId) {
        String url = HN_FB_BASE_URL + String.format(HN_FB_P_ITEM, storyId);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HNStory> bestStoriesResponse = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<HNStory>() {}
        );
        return bestStoriesResponse.getBody();
    }

    public HNComment fetchComment(String itemId) {
        String url = HN_FB_BASE_URL + String.format(HN_FB_P_ITEM, itemId);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HNComment> bestStoriesResponse = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<HNComment>() {}
        );
        return bestStoriesResponse.getBody();
    }
}
