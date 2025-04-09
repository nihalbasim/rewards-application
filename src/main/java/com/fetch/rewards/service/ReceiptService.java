package com.fetch.rewards.service;

import com.fetch.rewards.dto.PointsResponse;
import com.fetch.rewards.dto.ReceiptResponse;
import com.fetch.rewards.exception.InvalidReceiptException;
import com.fetch.rewards.exception.ReceiptNotFoundException;
import com.fetch.rewards.model.Receipt;
import com.fetch.rewards.util.PointsCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ReceiptService {

    private final ConcurrentHashMap<String, Integer> receiptStore = new ConcurrentHashMap<>();
    public ReceiptResponse processReceipt(Receipt receipt){
        int points = PointsCalculator.calculatePoints(receipt);
        String id = UUID.randomUUID().toString();
        receiptStore.put(id, points);
        return new ReceiptResponse(id);
    }

    public PointsResponse getPoints(String id) {
        if (!receiptStore.containsKey(id)) {
            throw new ReceiptNotFoundException(id);
        }
        int points = receiptStore.getOrDefault(id, 0);
        return new PointsResponse(points);
    }
}
