package com.maiia.pro.service;

import com.maiia.pro.entity.Patient;
import com.maiia.pro.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProPatientService {
    private final PatientRepository patientRepository;

    public ProPatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient find(String patientId) {
        return patientRepository.findById(patientId).orElseThrow();
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
