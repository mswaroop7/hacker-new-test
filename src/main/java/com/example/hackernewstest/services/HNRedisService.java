package com.example.hackernewstest.services;

import com.example.hackernewstest.beststories.models.HNStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HNRedisService {

    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name="redisTemplate")
    private ListOperations<String, HNStory> listOperations;

    @Resource(name="redisTemplate")
    private ValueOperations<String, Long> longValueOperations;

    @Autowired
    public HNRedisService(@Qualifier("redisTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void writeStories(String key, List<HNStory> stories) {
        listOperations.rightPushAll(key, stories);
    }

    public List<HNStory> readStories(String key) {
        return listOperations.range(key, 0, -1);
    }

    public void writeFetchTime(String key, long time) {
        longValueOperations.set(key, time);
    }

    public Long getFetchTime(String key) {
        return longValueOperations.get(key);
    }
}
