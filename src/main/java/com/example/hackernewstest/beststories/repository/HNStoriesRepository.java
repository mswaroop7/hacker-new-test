package com.example.hackernewstest.beststories.repository;

import com.example.hackernewstest.beststories.models.HNStory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HNStoriesRepository extends PagingAndSortingRepository<HNStory, String> {

}
