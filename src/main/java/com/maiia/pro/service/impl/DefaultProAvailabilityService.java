package com.maiia.pro.service.impl;

import com.maiia.pro.entity.Availability;
import com.maiia.pro.entity.TimeSlot;
import com.maiia.pro.repository.AppointmentRepository;
import com.maiia.pro.repository.AvailabilityRepository;
import com.maiia.pro.repository.TimeSlotRepository;
import com.maiia.pro.service.ProAvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class DefaultProAvailabilityService implements ProAvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    private final AppointmentRepository appointmentRepository;

    private final TimeSlotRepository timeSlotRepository;

    public DefaultProAvailabilityService(AppointmentRepository appointmentRepository, AvailabilityRepository availabilityRepository, TimeSlotRepository timeSlotRepository) {
        this.appointmentRepository = appointmentRepository;
        this.availabilityRepository = availabilityRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<Availability> findByPractitionerId(Integer practitionerId) {
        return availabilityRepository.findByPractitionerId(practitionerId);
    }

    public List<Availability> generateAvailabilities(Integer practitionerId) {
        List<Availability> availabilities = timeSlotRepository
                .findByPractitionerId(practitionerId)
                .stream()
                .flatMap(timeSlot -> generateAvailabilitiesForTimeSlot(timeSlot).stream())
                .collect(Collectors.toList());
        availabilityRepository.saveAll(availabilities);
        return availabilities;
    }

    private List<Availability> generateAvailabilitiesForTimeSlot(TimeSlot timeSlot) {
        long availabilityDuration = 15;
        List<Availability> availabilities = new ArrayList<>();
        LocalDateTime availabilityStartDate = timeSlot.getStartDate();
        while (availabilityStartDate.isBefore(timeSlot.getEndDate())) {
            LocalDateTime startDate = getStartDate(availabilityStartDate, availabilityStartDate.plusMinutes(availabilityDuration), timeSlot.getPractitionerId());
            if (startDate != null) {
                availabilityStartDate = startDate;
            } else {
                Availability newAvailability = Availability
                        .builder()
                        .practitionerId(timeSlot.getPractitionerId())
                        .startDate(availabilityStartDate)
                        .endDate(availabilityStartDate.plusMinutes(availabilityDuration))
                        .build();
                availabilityStartDate = availabilityStartDate.plusMinutes(availabilityDuration);
                availabilities.add(newAvailability);
            }
        }
        return availabilities;
    }

    private LocalDateTime getStartDate(LocalDateTime availabilityStartDate, LocalDateTime availabilityEndDate, Integer practitionerId) {
        LocalDateTime availabilityInDatabase = availabilityRepository
                .findAvailabilitiesByDateAndPractitionerId(availabilityStartDate, availabilityEndDate, practitionerId);
        LocalDateTime appointmentInDataBase = appointmentRepository
                .findAppointmentsByDateAndPatientId(availabilityStartDate, availabilityEndDate, practitionerId);
        return availabilityInDatabase != null ? availabilityInDatabase : appointmentInDataBase;
    }

}
