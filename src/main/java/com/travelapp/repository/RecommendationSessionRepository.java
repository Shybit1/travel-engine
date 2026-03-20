package com.travelapp.repository;

import com.travelapp.entity.RecommendationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationSessionRepository extends JpaRepository<RecommendationSession, Long> {
}
