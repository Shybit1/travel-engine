package com.travelapp.repository;

import com.travelapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByDestinationId(Long destinationId);

    @Query("SELECT r.destination.id, AVG(CAST(r.rating AS double)), COUNT(r) FROM Review r GROUP BY r.destination.id")
    List<Object[]> findAverageRatingAndCountGroupedByDestination();

    @Query("SELECT AVG(CAST(r.rating AS double)), COUNT(r) FROM Review r WHERE r.destination.id = :destinationId")
    List<Object[]> findAverageRatingAndCountByDestinationId(@Param("destinationId") Long destinationId);
}
