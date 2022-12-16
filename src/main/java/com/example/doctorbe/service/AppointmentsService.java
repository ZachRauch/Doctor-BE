package com.example.doctorbe.service;

import com.example.doctorbe.models.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class AppointmentsService {
    private Long nextAppointmentId = 0L;
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public void addNewAppointment(Appointment appointment) throws Exception {

        for (Appointment existingAppointment: appointments) {
            if (existingAppointment.id.equals(appointment.id))
                throw new Exception();
        }
        appointment.id = nextAppointmentId++;
        appointments.add(appointment);
    }


    public Iterable<Appointment> getAppointments( Long patientId, Long doctorId) {
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


    public void deleteById(Long id) throws Exception {
        for (var appointment: appointments) {
            if (appointment.id.equals(id)) {
                appointments.remove(appointment);
                return;
            }
        }

        throw new Exception();
    }


    public void modifyAppointment(Long id, Appointment appointment) throws Exception {
        if (!id.equals(appointment.id))
            throw new Exception();
        for (var existingAppointment: appointments) {
            if (id.equals(existingAppointment.id)) {
                appointments.remove(existingAppointment);
                appointments.add(appointment);
                return;
            }
        }

        throw new Exception();
    }
}
