package com.maiia.pro.service.impl;

import com.maiia.pro.entity.Patient;
import com.maiia.pro.repository.PatientRepository;
import com.maiia.pro.service.ProPatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProPatientService implements ProPatientService {
    private final PatientRepository patientRepository;

    public DefaultProPatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient find(String patientId) {
        return patientRepository.findById(patientId).orElseThrow();
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
