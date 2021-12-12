package com.maiia.pro.controller;

import com.maiia.pro.entity.Appointment;
import com.maiia.pro.service.ProAppointmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProAppointmentController {
    private final ProAppointmentService proAppointmentService;

    public ProAppointmentController(ProAppointmentService proAppointmentService) {
        this.proAppointmentService = proAppointmentService;
    }

    @ApiOperation(value = "Get appointments by practitionerId")
    @GetMapping("/{practitionerId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPractitioner(@PathVariable final String practitionerId) {
        return new ResponseEntity<>(proAppointmentService.findByPractitionerId(practitionerId), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all appointments")
    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments() {
        return new ResponseEntity<>(proAppointmentService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add new appointment")
    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(proAppointmentService.addAppointment(appointment), HttpStatus.CREATED);

    }
}
