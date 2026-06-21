package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AppointmentService {
    private final DataStore<Appointment> appointments;
    private final List<AppointmentObserver> observers = new ArrayList<>();
    private final Timer reminderTimer = new Timer("appointment-reminders", true);
    public AppointmentService(DataStore<Appointment> appointments) { this.appointments = appointments; }
    public synchronized Appointment createAppointment(Patient patient, Doctor doctor, LocalDate date, String reason) { Appointment a = new Appointment(IdGenerator.lazyInstance().nextAppointmentId(), patient, doctor, date, reason); appointments.save(a); notifyObservers(a); scheduleReminder(a); return a; }
    public void save(Appointment appointment) { appointments.save(appointment); }
    public List<Appointment> listAppointments() { return appointments.findAll(); }
    public Appointment findByIdOrThrow(String id) { return appointments.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id)); }
    public Appointment cancelAppointment(String id) { Appointment a = findByIdOrThrow(id); a.cancel(); appointments.save(a); return a; }
    public List<Appointment> findByStatus(AppointmentStatus status) { return appointments.findAll().stream().filter(a -> a.getStatus() == status).collect(Collectors.toList()); }
    public Map<String, Long> appointmentsPerDoctor() { return appointments.findAll().stream().collect(Collectors.groupingBy(a -> a.getDoctor().getName(), ConcurrentHashMap::new, Collectors.counting())); }
    public void addObserver(AppointmentObserver observer) { observers.add(observer); }
    private void notifyObservers(Appointment appointment) { observers.forEach(o -> o.onAppointmentCreated(appointment)); }
    private void scheduleReminder(Appointment appointment) { reminderTimer.schedule(new TimerTask() { @Override public void run() { System.out.println("Reminder: upcoming appointment " + appointment.getDisplayName()); } }, 1000L); }
}
