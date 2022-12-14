package com.example.doctorbe.controllers;

import com.example.doctorbe.models.Appointment;
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
    private Long nextAppointmentId = 0L;
    private ArrayList<Appointment> appointments = new ArrayList<>();

    @PostMapping
    public void addNewAppointment(@RequestBody Appointment appointment) {
//        try {
//        var something = methodCall()
//        catch (Exception e) { }

        for (Appointment existingAppointment: appointments) {
            if (existingAppointment.id.equals(appointment.id))
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
        appointment.id = nextAppointmentId++;
        appointments.add(appointment);
    }

    @GetMapping
    public Iterable<Appointment> getAppointments(@RequestParam(required = false) Long patientId,
                                                 @RequestParam(required = false) Long doctorId) {
        if (patientId != null) {
            final var matching = new ArrayList<Appointment>();

            for (Appointment appointment : appointments)
                if (patientId.equals(appointment.patientId)) {
                    matching.add(appointment);
                }
            return matching;
        }
        if (doctorId != null) {
            final var matching = new ArrayList<Appointment>();

            for (Appointment appointment : appointments)
                if (doctorId.equals(appointment.doctorId)) {
                    matching.add(appointment);
                }
            return matching;
        }
        return appointments;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        for (var appointment: appointments) {
            if (appointment.id.equals(id)) {
                appointments.remove(appointment);
                return;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public void modifyAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        if (!id.equals(appointment.id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        for (var existingAppointment: appointments) {
            if (id.equals(existingAppointment.id)) {
                appointments.remove(existingAppointment);
                appointments.add(appointment);
                return;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
