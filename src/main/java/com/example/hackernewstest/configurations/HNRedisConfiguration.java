package com.example.hackernewstest.configurations;

import static com.example.hackernewstest.constants.HNRedisConstants.HOST;
import static com.example.hackernewstest.constants.HNRedisConstants.PORT;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class HNRedisConfiguration {

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(
                new RedisStandaloneConfiguration(HOST, PORT));
    }

    @Bean
    @Qualifier("redisTemplate")
    RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template =  new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JsonRedisSerializer());
        return template;
    }

    static class JsonRedisSerializer implements RedisSerializer<Object> {

        private final ObjectMapper objectMapper;

        public JsonRedisSerializer() {
            this.objectMapper = new ObjectMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL,
                    JsonTypeInfo.As.PROPERTY);
        }

        @Override
        public byte[] serialize(Object t) throws SerializationException {
            try {
                return objectMapper.writeValueAsBytes(t);
            } catch (JsonProcessingException e) {
                throw new SerializationException(e.getMessage(), e);
            }
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            if(bytes == null){
                return null;
            }

            try {
                return objectMapper.readValue(bytes, Object.class);
            } catch (Exception e) {
                throw new SerializationException(e.getMessage(), e);
            }
        }
    }
}
