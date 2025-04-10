package com.fetch.rewards.controller;

import com.fetch.rewards.dto.PointsResponse;
import com.fetch.rewards.dto.ReceiptResponse;
import com.fetch.rewards.model.Receipt;
import com.fetch.rewards.service.ReceiptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/process")
    public ReceiptResponse processReceipt(@Valid @RequestBody Receipt receipt){
        return receiptService.processReceipt(receipt);
    }

    @GetMapping("/{id}/points")
    public PointsResponse getPoints(@PathVariable String id) {
        return receiptService.getPoints(id);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Rewards Application is up and running");
    }
}
