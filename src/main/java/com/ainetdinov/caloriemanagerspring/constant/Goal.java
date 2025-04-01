package com.ainetdinov.caloriemanagerspring.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Goal {
    WEIGHT_LOSS(0.9),
    WEIGHT_MAINTENANCE(1),
    WEIGHT_GAIN(1.15);

    private final double ratio;
}
