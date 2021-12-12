package com.maiia.pro.repository;

import com.maiia.pro.entity.Availability;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, String> {
    List<Availability> findByPractitionerId(Integer id);

    @Query("select a.endDate from Availability AS a  where a.practitionerId = ?3 and ((a.startDate  <= ?1 and a.endDate > ?1) or (a.startDate  < ?2 and a.endDate >= ?2))")
    LocalDateTime findAvailabilitiesByDateAndPractitionerId(LocalDateTime startDate, LocalDateTime EndDate, Integer PractitionerId);

    @Modifying
    @Query("delete from Availability a where a.startDate <= ?1  and a.endDate > ?1")
    void deleteAvailabilityByDate(LocalDateTime id);
}
