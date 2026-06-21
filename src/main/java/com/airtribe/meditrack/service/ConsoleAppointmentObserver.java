package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;

public class ConsoleAppointmentObserver implements AppointmentObserver {
    @Override public void onAppointmentCreated(Appointment appointment) { System.out.println("Reminder scheduled for appointment " + appointment.getId() + ": " + appointment.getDisplayName()); }
}
