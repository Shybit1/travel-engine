package com.travelapp.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(mappedBy = "destinations")
    private List<Itinerary> itineraries;

    public enum Category {
        BEACH, CITY, MOUNTAIN, HISTORIC, NATURE
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
    public List<Itinerary> getItineraries() { return itineraries; }
    public void setItineraries(List<Itinerary> itineraries) { this.itineraries = itineraries; }
}
