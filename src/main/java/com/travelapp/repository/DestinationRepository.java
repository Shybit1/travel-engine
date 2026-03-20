package com.travelapp.repository;

import com.travelapp.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("SELECT d FROM Destination d WHERE " +
           "(:continent IS NULL OR d.continent = :continent) AND " +
           "(:budgetLevel IS NULL OR d.budgetLevel = :budgetLevel) AND " +
           "(:climate IS NULL OR d.climate = :climate) AND " +
           "(:tripType IS NULL OR d.tripType = :tripType)")
    List<Destination> findByFilters(
            @Param("continent") Destination.Continent continent,
            @Param("budgetLevel") Destination.BudgetLevel budgetLevel,
            @Param("climate") Destination.Climate climate,
            @Param("tripType") Destination.TripType tripType);
}
