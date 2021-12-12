package com.maiia.pro.controller;

import com.maiia.pro.entity.Availability;
import com.maiia.pro.service.ProAvailabilityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/availabilities", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProAvailabilityController {
    private final ProAvailabilityService proAvailabilityService;

    public ProAvailabilityController(ProAvailabilityService proAvailabilityService) {
        this.proAvailabilityService = proAvailabilityService;
    }

    @ApiOperation(value = "Get availabilities by practitionerId")
    @GetMapping
    public ResponseEntity<List<Availability>> getAvailabilities(@RequestParam final Integer practitionerId) {
        return new ResponseEntity<>(proAvailabilityService.findByPractitionerId(practitionerId), HttpStatus.OK);
    }
}
