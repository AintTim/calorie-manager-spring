package com.ainetdinov.caloriemanagerspring.controller;

import com.ainetdinov.caloriemanagerspring.exception.InvalidDateBoundaryException;
import com.ainetdinov.caloriemanagerspring.exception.NoSuchUserException;
import com.ainetdinov.caloriemanagerspring.model.entity.DailyReport;
import com.ainetdinov.caloriemanagerspring.model.entity.ExtendedReport;
import com.ainetdinov.caloriemanagerspring.service.MealService;
import com.ainetdinov.caloriemanagerspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

import static com.ainetdinov.caloriemanagerspring.constant.WebConstant.*;

@RestController
@RequestMapping(API + REPORTS)
@RequiredArgsConstructor
public class ReportController {
    private final UserService userService;
    private final MealService mealService;

    @GetMapping("/{userId}")
    public ResponseEntity<DailyReport> getDailyReport(@PathVariable Long userId,
                                                      @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) {
        date = setCurrentDateIfAbsent(date);
        return ResponseEntity.ok(mealService.getDailyReport(userId, date));
    }

    @GetMapping("/check-limit/{userId}")
    public ResponseEntity<String> validateUserCalorieLimit(@PathVariable Long userId,
                                                           @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) {
        date = setCurrentDateIfAbsent(date);
        DailyReport dailyReport = mealService.getDailyReport(userId, date);
        String message = dailyReport.isWithinLimit()
                ? String.format(KEPT_WITHIN_LIMIT_MESSAGE, userId, dailyReport.getCalorieLimit())
                : String.format(EXCEEDED_LIMIT_MESSAGE, userId, dailyReport.getCalorieLimit(), dailyReport.getCalories() - dailyReport.getCalorieLimit());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<ExtendedReport> getExtendedReport(@PathVariable Long userId,
                                                            @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate start,
                                                            @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate end) {
        start = setCurrentDateIfAbsent(start);
        if (start.isAfter(end)) {
            throw new InvalidDateBoundaryException(start, end);
        }
        var user = userService.findById(userId);
        if (user.isEmpty()) {
            throw new NoSuchUserException(userId);
        }
        return ResponseEntity.ok(mealService.getExtendedReport(user.get(), start, end));
    }

    private LocalDate setCurrentDateIfAbsent(LocalDate date) {
        return Objects.isNull(date) ? LocalDate.now() : date;
    }
}
