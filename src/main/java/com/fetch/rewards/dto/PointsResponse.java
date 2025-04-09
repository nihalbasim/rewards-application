package com.fetch.rewards.dto;

import lombok.Getter;
import lombok.Setter;

public class PointsResponse {

    @Getter @Setter
    private int points;

    public PointsResponse(int points) {
        this.points = points;
    }
}
