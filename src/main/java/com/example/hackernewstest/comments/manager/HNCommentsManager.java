package com.example.hackernewstest.comments.manager;

import com.example.hackernewstest.beststories.models.HNStory;
import com.example.hackernewstest.comments.models.HNComment;
import com.example.hackernewstest.services.HNFirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class HNCommentsManager {

    private final HNFirebaseService firebaseService;

    @Autowired
    public HNCommentsManager(HNFirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    public List<HNComment> fetchCommentsForStoryId(String storyId) {
        HNStory story = firebaseService.fetchStory(storyId);
        TreeSet<HNComment> comments = new TreeSet<>(new HNCommentsComparator());
        if (story.getKids() == null) {
            return new ArrayList<>();
        }
        story.getKids().forEach(s -> {
            HNComment comment = fetchComment(s);
            comments.add(comment);
        });
        return comments.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    public HNComment fetchComment(String id) {
        return firebaseService.fetchComment(id);
    }
}
