package com.ainetdinov.caloriemanagerspring.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Sex {
    MALE(66.5, 13.75, 5.003, 6.775),
    FEMALE(655.1, 9.563, 1.85, 4.676);

    private final double ratio;
    private final double weight;
    private final double height;
    private final double age;
}
