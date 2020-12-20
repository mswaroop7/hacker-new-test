package com.example.hackernewstest.beststories.controller;

import com.example.hackernewstest.beststories.manager.HNBestStoriesManager;
import com.example.hackernewstest.beststories.models.HNStory;
import com.example.hackernewstest.models.HNApiResponse;
import com.example.hackernewstest.utils.HNResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/")
public class HNBestStoriesController {

    private final HNBestStoriesManager storiesManager;

    @Autowired
    public HNBestStoriesController(HNBestStoriesManager storiesManager) {
        this.storiesManager = storiesManager;
    }

    @GetMapping("/best-stories")
    public HNApiResponse getBestStories() {
        try {
            return HNResponseUtil
                    .createSuccessResponse(storiesManager.getBestStories());
        }
        catch (Exception e) {
            return HNResponseUtil
                    .createErrorResponse(e.getMessage());
        }
    }

    @GetMapping("/past-stories")
    public HNApiResponse getPastBestStories(@RequestParam(name="page") Integer page) {
        try {
            return HNResponseUtil
                    .createSuccessResponse(storiesManager.getPastBestStories(page));
        }
        catch (Exception e) {
            return HNResponseUtil
                    .createErrorResponse(e.getMessage());
        }
    }
}
