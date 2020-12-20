package com.example.hackernewstest.comments.controller;

import com.example.hackernewstest.comments.manager.HNCommentsManager;
import com.example.hackernewstest.models.HNApiResponse;
import com.example.hackernewstest.utils.HNResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
public class HNCommentsController {

    private final HNCommentsManager commentsManager;

    @Autowired
    public HNCommentsController(HNCommentsManager commentsManager) {
        super();
        this.commentsManager = commentsManager;
    }

    @GetMapping("/comments")
    public HNApiResponse getComments(@RequestParam(name = "itemId") String itemId) {
        try {
            return HNResponseUtil
                    .createSuccessResponse(commentsManager
                            .fetchCommentsForStoryId(itemId));
        }
        catch (Exception e) {
            return HNResponseUtil
                    .createErrorResponse(e.getMessage());
        }
    }
}
