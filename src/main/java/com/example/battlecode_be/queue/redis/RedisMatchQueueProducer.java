package com.example.battlecode_be.queue.redis;

import com.example.battlecode_be.dto.MatchQueueMessage;
import com.example.battlecode_be.queue.MatchQueueProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class RedisMatchQueueProducer implements MatchQueueProducer {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Value("${app.queue.match}")
    private String queueName;

    @Override
    public void enqueue(MatchQueueMessage message) {
        try {
            String json = objectMapper.writeValueAsString(message);
            redisTemplate.opsForList().leftPush(queueName, json);
        } catch (JsonParseException e) {
            throw new RuntimeException("Failed to serialize match queue message", e);
        }
    }
}


