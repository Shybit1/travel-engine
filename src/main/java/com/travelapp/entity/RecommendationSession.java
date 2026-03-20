package com.travelapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendation_sessions")
public class RecommendationSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "trip_type", nullable = false)
    private Destination.TripType tripType;

    @Enumerated(EnumType.STRING)
    @Column(name = "budget_level", nullable = false)
    private Destination.BudgetLevel budgetLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Destination.Climate climate;

    @Column(nullable = false)
    private String continent;

    @Column(name = "duration_days")
    private Integer durationDays;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Destination.TripType getTripType() { return tripType; }
    public void setTripType(Destination.TripType tripType) { this.tripType = tripType; }
    public Destination.BudgetLevel getBudgetLevel() { return budgetLevel; }
    public void setBudgetLevel(Destination.BudgetLevel budgetLevel) { this.budgetLevel = budgetLevel; }
    public Destination.Climate getClimate() { return climate; }
    public void setClimate(Destination.Climate climate) { this.climate = climate; }
    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }
    public Integer getDurationDays() { return durationDays; }
    public void setDurationDays(Integer durationDays) { this.durationDays = durationDays; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
