package com.maiia.pro.repository;

import com.maiia.pro.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, String> {
    List<Appointment> findByPractitionerId(String practitionerId);

    List<Appointment> findAll();

    @Query("select a.endDate from Appointment AS a  where a.practitionerId = ?3 and ((a.startDate  <= ?1 and a.endDate > ?1) or (a.startDate  < ?2 and a.endDate >= ?2))")
    LocalDateTime findAppointmentsByDateAndPatientId(LocalDateTime startDate, LocalDateTime endDate, Integer PractitionerId);
}
