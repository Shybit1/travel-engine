package com.travelapp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "destinations", indexes = {
    @Index(name = "idx_destination_name", columnList = "name"),
    @Index(name = "idx_destination_country", columnList = "country")
})
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Continent continent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Climate climate;

    @Enumerated(EnumType.STRING)
    @Column(name = "budget_level", nullable = false)
    private BudgetLevel budgetLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "trip_type", nullable = false)
    private TripType tripType;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column(name = "min_duration")
    private Integer minDuration;

    @Column(name = "max_duration")
    private Integer maxDuration;

    private String language;

    @Column(name = "best_time")
    private String bestTime;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "destination")
    private List<Itinerary> itineraries;

    public enum Continent {
        Asia, Europe, Americas, Africa, Oceania
    }

    public enum Climate {
        Tropical, Arid, Temperate, Cold
    }

    public enum BudgetLevel {
        Budget, Mid_Range, Luxury
    }

    public enum TripType {
        Relaxation, Adventure, Culture, City
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Continent getContinent() { return continent; }
    public void setContinent(Continent continent) { this.continent = continent; }
    public Climate getClimate() { return climate; }
    public void setClimate(Climate climate) { this.climate = climate; }
    public BudgetLevel getBudgetLevel() { return budgetLevel; }
    public void setBudgetLevel(BudgetLevel budgetLevel) { this.budgetLevel = budgetLevel; }
    public TripType getTripType() { return tripType; }
    public void setTripType(TripType tripType) { this.tripType = tripType; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    public Integer getMinDuration() { return minDuration; }
    public void setMinDuration(Integer minDuration) { this.minDuration = minDuration; }
    public Integer getMaxDuration() { return maxDuration; }
    public void setMaxDuration(Integer maxDuration) { this.maxDuration = maxDuration; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getBestTime() { return bestTime; }
    public void setBestTime(String bestTime) { this.bestTime = bestTime; }
    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
    public List<Itinerary> getItineraries() { return itineraries; }
    public void setItineraries(List<Itinerary> itineraries) { this.itineraries = itineraries; }
}
