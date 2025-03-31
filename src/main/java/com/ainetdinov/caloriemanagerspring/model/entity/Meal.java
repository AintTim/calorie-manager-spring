package com.ainetdinov.caloriemanagerspring.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Entity
@Table(name = "meals")
public class Meal implements Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "meal_to_dish",
            joinColumns = {
                    @JoinColumn(name = "meal_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "dish_id", referencedColumnName = "id")
            }
    )
    @Size(min = 1, max = 5)
    private List<Dish> dishes;
    private LocalDate date;
    @Positive
    private Double calories;
}
