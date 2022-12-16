package com.example.doctorbe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public Long doctorId;
    public Long patientId;
    public Date date;
    public int slot;

    public Appointment(Long id, Long doctorId, Long patientId, Date date, int slot) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.slot = slot;
    }

    public Appointment() {
    }
}
