package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;
import java.time.LocalDate;

public class Appointment extends MedicalEntity implements Cloneable {
    private static final long serialVersionUID = 1L;
    private Patient patient;
    private Doctor doctor;
    private LocalDate appointmentDate;
    private AppointmentStatus status;
    private String reason;

    public Appointment(String id, Patient patient, Doctor doctor, LocalDate appointmentDate, String reason) {
        super(id);
        setPatient(patient);
        setDoctor(doctor);
        setAppointmentDate(appointmentDate);
        setReason(reason);
        this.status = AppointmentStatus.PENDING;
    }

    public Patient getPatient() { return patient; }
    public final void setPatient(Patient patient) { this.patient = Validator.requireNonNull(patient, "patient"); }
    public Doctor getDoctor() { return doctor; }
    public final void setDoctor(Doctor doctor) { this.doctor = Validator.requireNonNull(doctor, "doctor"); }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public final void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = Validator.requireNonNull(appointmentDate, "appointment date"); }
    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = Validator.requireNonNull(status, "status"); }
    public String getReason() { return reason; }
    public final void setReason(String reason) { Validator.requireText(reason, "reason"); this.reason = reason.trim(); }
    public void cancel() { setStatus(AppointmentStatus.CANCELLED); }
    @Override public String getDisplayName() { return patient.getName() + " with " + doctor.getName() + " on " + appointmentDate; }
    @Override public Appointment clone() { try { Appointment a = (Appointment) super.clone(); a.patient = patient.clone(); return a; } catch (CloneNotSupportedException ex) { throw new AssertionError(ex); } }
    @Override public String toString() { return "Appointment{id='" + getId() + "', patient=" + patient.getName() + ", doctor=" + doctor.getName() + ", date=" + appointmentDate + ", status=" + status + ", reason='" + reason + "'}"; }
}
