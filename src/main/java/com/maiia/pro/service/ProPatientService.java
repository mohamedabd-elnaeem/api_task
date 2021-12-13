package com.maiia.pro.service;

import com.maiia.pro.entity.Patient;

import java.util.List;

public interface ProPatientService {

    Patient find(String patientId);

    List<Patient> findAll();
}
