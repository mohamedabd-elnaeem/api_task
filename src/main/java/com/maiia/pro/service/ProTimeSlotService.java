package com.maiia.pro.service;

import com.maiia.pro.entity.TimeSlot;
import com.maiia.pro.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProTimeSlotService {
    private final TimeSlotRepository timeSlotRepository;

    public ProTimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> findByPractitionerId(Integer practitionerId) {
        return timeSlotRepository.findByPractitionerId(practitionerId);
    }
}
