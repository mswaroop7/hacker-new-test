package com.example.hackernewstest.beststories.manager;

import static com.example.hackernewstest.constants.HNRedisConstants.K_BEST_STORIES;
import static com.example.hackernewstest.constants.HNRedisConstants.K_BEST_STORIES_FETCH_TIME;
import com.example.hackernewstest.beststories.models.HNStory;
import com.example.hackernewstest.beststories.repository.HNStoriesRepository;
import com.example.hackernewstest.services.HNFirebaseService;
import com.example.hackernewstest.services.HNRedisService;
import com.example.hackernewstest.utils.HNTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HNBestStoriesManager {

    private final HNFirebaseService firebaseService;
    private final HNRedisService redisService;
    private final HNStoriesRepository storiesRepository;

    @Autowired
    public HNBestStoriesManager(HNFirebaseService firebaseService,
                                HNRedisService redisService,
                                HNStoriesRepository storiesRepository) {
        this.firebaseService = firebaseService;
        this.redisService = redisService;
        this.storiesRepository = storiesRepository;
    }

    public List<HNStory> getBestStories() {
        Long lastFetchTime = redisService.getFetchTime(K_BEST_STORIES_FETCH_TIME);
        Long difference = HNTimeUtils.differenceFromCurrentTime(lastFetchTime);
        return difference > 15 ? getBestStoriesFromService()
                : getBestStoriesFromCache();
    }

    private List<HNStory> getBestStoriesFromCache()  {
        return redisService.readStories(K_BEST_STORIES);
    }

    private List<HNStory> getBestStoriesFromService() {
        List<HNStory> stories = firebaseService.fetchBestStories();
        saveStoriesInCache(stories);
        saveStoriesInDb(stories);
        return stories;
    }

    private void saveStoriesInCache(List<HNStory> stories) {
        redisService.writeStories(K_BEST_STORIES, stories);
        redisService.writeFetchTime(K_BEST_STORIES_FETCH_TIME,
                System.currentTimeMillis());
    }

    private void saveStoriesInDb(List<HNStory> stories) {
        storiesRepository.saveAll(stories);
    }

    public List<HNStory> getPastBestStories(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        List<HNStory> stories = new ArrayList<>();
        storiesRepository.findAll(pageable).forEach(stories::add);
        return stories;
    }
}