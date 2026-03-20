package com.travelapp.controller;

import com.travelapp.entity.Destination;
import com.travelapp.repository.DestinationRepository;
import com.travelapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getDestinations(
            @RequestParam(required = false) String continent,
            @RequestParam(required = false) String budgetLevel,
            @RequestParam(required = false) String climate,
            @RequestParam(required = false) String tripType) {

        Destination.Continent continentEnum = parseEnum(Destination.Continent.class, continent);
        Destination.BudgetLevel budgetEnum = parseBudgetLevel(budgetLevel);
        Destination.Climate climateEnum = parseEnum(Destination.Climate.class, climate);
        Destination.TripType tripTypeEnum = parseEnum(Destination.TripType.class, tripType);

        List<Destination> destinations = destinationRepository.findByFilters(continentEnum, budgetEnum, climateEnum, tripTypeEnum);
        List<Object[]> statsList = reviewRepository.findAverageRatingAndCountGroupedByDestination();

        Map<Long, Double> avgRatings = new HashMap<>();
        Map<Long, Long> reviewCounts = new HashMap<>();

        for (Object[] stats : statsList) {
            Long destId = (Long) stats[0];
            Double avgRating = (Double) stats[1];
            Long count = (Long) stats[2];
            avgRatings.put(destId, avgRating != null ? avgRating : 0.0);
            reviewCounts.put(destId, count != null ? count : 0L);
        }

        List<Map<String, Object>> response = destinations.stream().map(d -> {
            Map<String, Object> map = convertDestinationToMap(d);
            map.put("avgRating", avgRatings.getOrDefault(d.getId(), 0.0));
            map.put("reviewCount", reviewCounts.getOrDefault(d.getId(), 0L));
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getDestination(@PathVariable Long id) {
        return destinationRepository.findById(id).map(d -> {
            Map<String, Object> map = convertDestinationToMap(d);
            
            List<Object[]> statsList = reviewRepository.findAverageRatingAndCountByDestinationId(id);
            if (statsList != null && !statsList.isEmpty()) {
                Object[] stats = statsList.get(0);
                Double avgRating = (Double) stats[0];
                Long count = (Long) stats[1];
                map.put("avgRating", avgRating != null ? avgRating : 0.0);
                map.put("reviewCount", count != null ? count : 0L);
            } else {
                map.put("avgRating", 0.0);
                map.put("reviewCount", 0L);
            }
            
            return ResponseEntity.ok(map);
        }).orElse(ResponseEntity.notFound().build());
    }

    private Map<String, Object> convertDestinationToMap(Destination d) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", d.getId());
        map.put("name", d.getName());
        map.put("country", d.getCountry());
        map.put("description", d.getDescription());
        map.put("continent", d.getContinent());
        map.put("climate", d.getClimate());
        map.put("budgetLevel", d.getBudgetLevel());
        map.put("tripType", d.getTripType());
        map.put("imageUrl", d.getImageUrl());
        map.put("latitude", d.getLatitude());
        map.put("longitude", d.getLongitude());
        map.put("minDuration", d.getMinDuration());
        map.put("maxDuration", d.getMaxDuration());
        map.put("language", d.getLanguage());
        map.put("bestTime", d.getBestTime());
        return map;
    }

    private <E extends Enum<E>> E parseEnum(Class<E> enumClass, String value) {
        if (value == null || value.isEmpty()) return null;
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            // Check if there's an ignore-case match
            for (E enumValue : enumClass.getEnumConstants()) {
                if (enumValue.name().equalsIgnoreCase(value)) {
                    return enumValue;
                }
            }
            return null;
        }
    }

    private Destination.BudgetLevel parseBudgetLevel(String value) {
        if (value == null || value.isEmpty()) return null;
        return Destination.BudgetLevel.fromString(value);
    }
}
