package com.ainetdinov.caloriemanagerspring.model.entity;

import com.ainetdinov.caloriemanagerspring.constant.Goal;
import com.ainetdinov.caloriemanagerspring.constant.Sex;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Min(1)
    @Max(100)
    private Integer age;
    @Min(5)
    @Max(250)
    private Double weight;
    @Min(40)
    @Max(250)
    private Double height;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Enumerated(EnumType.STRING)
    private Goal goal;
    private Double dailyCalories;
}
