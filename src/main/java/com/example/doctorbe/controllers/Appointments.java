package com.example.doctorbe.controllers;

import com.example.doctorbe.models.Appointment;
import com.example.doctorbe.service.AppointmentsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/appointments")
//CORS Error: Cross origin request, 'origin' = server
//browser is connected to localhost:4200
//The app on 4200 is making a request to localhost:8080
//Browser to make sure that the origin at 8080 is ok with sending data to the origin at 4200
// Add an allowed-origin header to all responses with a * wildcard
@CrossOrigin
public class Appointments {
    private final AppointmentsService service;
    public Appointments(AppointmentsService service) {
        this.service = service;
    }

    @PostMapping
    public void addNewAppointment(@RequestBody Appointment appointment) {
        try {
            service.addNewAppointment(appointment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @GetMapping
    public Iterable<Appointment> getAppointments(@RequestParam(required = false) Long patientId,
                                                 @RequestParam(required = false) Long doctorId) {
        return service.getAppointments(patientId, doctorId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void modifyAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        try {
            service.modifyAppointment(id, appointment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
