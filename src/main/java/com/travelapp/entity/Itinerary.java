package com.travelapp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "itineraries", indexes = {
    @Index(name = "idx_itinerary_destination", columnList = "destination_id")
})
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", foreignKey = @ForeignKey(name = "fk_itinerary_destination"))
    private Destination destination;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "day_plan", columnDefinition = "TEXT")
    private String dayPlan; // stores JSON as text

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Destination getDestination() { return destination; }
    public void setDestination(Destination destination) { this.destination = destination; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getDayPlan() { return dayPlan; }
    public void setDayPlan(String dayPlan) { this.dayPlan = dayPlan; }
}
