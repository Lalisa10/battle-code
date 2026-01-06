package com.example.battlecode_be.queue;

import com.example.battlecode_be.dto.MatchQueueMessage;

public interface MatchQueueProducer {
    void enqueue(MatchQueueMessage message);
}
