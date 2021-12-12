package com.maiia.pro.service;

import com.maiia.pro.entity.Appointment;
import com.maiia.pro.repository.AppointmentRepository;
import com.maiia.pro.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProAppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AvailabilityRepository availabilityRepository;

    public ProAppointmentService(AppointmentRepository appointmentRepository, AvailabilityRepository availabilityRepository) {
        this.appointmentRepository = appointmentRepository;
        this.availabilityRepository = availabilityRepository;
    }

    public Appointment find(String appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow();
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> findByPractitionerId(String practitionerId) {
        return appointmentRepository.findByPractitionerId(practitionerId);
    }

    public Appointment addAppointment(Appointment appointment) {
        availabilityRepository.deleteAvailabilityByDate(appointment.getStartDate());
        return appointmentRepository.save(appointment);
    }
}
