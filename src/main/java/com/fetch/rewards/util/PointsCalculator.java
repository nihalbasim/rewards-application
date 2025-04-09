package com.fetch.rewards.util;

import com.fetch.rewards.exception.InvalidReceiptException;
import com.fetch.rewards.model.Item;
import com.fetch.rewards.model.Receipt;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
public class PointsCalculator {

    public static int calculatePoints(Receipt receipt){
        int points = 0;
        try {
            points += pointsForRetailerName(receipt.getRetailer());
            points += pointsForRoundDollarAmount(receipt.getTotal());
            points += pointsForQuarterMultiple(receipt.getTotal());
            points += pointsForItemsCount(receipt.getItems());
            points += pointsForItemDescriptions(receipt.getItems());
            points += pointsForOddPurchaseDay(receipt.getPurchaseDate());
            points += pointsForPurchaseTime(receipt.getPurchaseTime());
            return points;
        } catch (Exception ex) {
            log.warn("Error processing receipt: {}", ex.getMessage());
            throw new InvalidReceiptException(ex);
        }
    }

    private static int pointsForRetailerName(String retailer) {
        return retailer.replaceAll("[^a-zA-Z0-9]", "").length();
    }

    private static int pointsForRoundDollarAmount(String total) {
        if (new BigDecimal(total).compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total amount must be positive");
        }
        return new BigDecimal(total).stripTrailingZeros().scale() == 0 ? 50 : 0;
    }

    private static int pointsForQuarterMultiple(String total) {
        return new BigDecimal(total).remainder(new BigDecimal("0.25"))
                .compareTo(BigDecimal.ZERO) == 0 ? 25 : 0;
    }

    private static int pointsForItemsCount(List<Item> items) {
        return (items.size() / 2) * 5;
    }

    private static int pointsForItemDescriptions(List<Item> items) {
        int points = 0;
        for (Item item : items) {
            if (item.getShortDescription().trim().length() % 3 == 0) {
                BigDecimal itemPrice = new BigDecimal(item.getPrice());
                if (itemPrice.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException(String.format("Item %s price must be positive", item.getShortDescription()));
                }
                points += (int) Math.ceil(itemPrice.multiply(new BigDecimal("0.2")).doubleValue());
            }
        }
        return points;
    }

    private static int pointsForOddPurchaseDay(String purchaseDate) {
        LocalDate date = LocalDate.parse(purchaseDate);
        if (!date.isBefore(LocalDate.now()) && !date.isEqual(LocalDate.now())) {
            throw new IllegalArgumentException("Purchase date must be in the past or today");
        }
        return date.getDayOfMonth() % 2 != 0 ? 6 : 0;
    }

    private static int pointsForPurchaseTime(String purchaseTime) {
        LocalTime time = LocalTime.parse(purchaseTime);
        return (time.isAfter(LocalTime.of(13, 59, 59))
                && time.isBefore(LocalTime.of(16, 0, 1))) ? 10 : 0;
    }
}
