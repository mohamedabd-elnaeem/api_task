package com.maiia.pro.service;

import com.maiia.pro.entity.Appointment;

import java.util.List;

public interface ProAppointmentService {

    List<Appointment> findAll();

    List<Appointment> findByPractitionerId(String practitionerId);

    Appointment addAppointment(Appointment appointment);
}
