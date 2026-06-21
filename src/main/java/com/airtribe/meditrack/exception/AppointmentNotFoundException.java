package com.airtribe.meditrack.exception;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String id) { super("Appointment not found: " + id); }
    public AppointmentNotFoundException(String id, Throwable cause) { super("Appointment not found: " + id, cause); }
}
