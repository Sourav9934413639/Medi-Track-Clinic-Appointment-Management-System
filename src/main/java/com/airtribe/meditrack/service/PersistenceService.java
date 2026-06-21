package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.DateUtil;
import com.airtribe.meditrack.util.SerializationUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersistenceService {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    public PersistenceService(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService) { this.patientService = patientService; this.doctorService = doctorService; this.appointmentService = appointmentService; }
    public void loadCsvData() {
        for (List<String> r : CSVUtil.readCsv(Constants.PATIENT_CSV)) patientService.save(new Patient(r.get(0), r.get(1), Integer.parseInt(r.get(2)), r.get(3), r.get(4), r.get(5)));
        for (List<String> r : CSVUtil.readCsv(Constants.DOCTOR_CSV)) doctorService.save(new Doctor(r.get(0), r.get(1), Integer.parseInt(r.get(2)), r.get(3), r.get(4), Specialization.valueOf(r.get(5)), new BigDecimal(r.get(6))));
        for (List<String> r : CSVUtil.readCsv(Constants.APPOINTMENT_CSV)) { Optional<Patient> p = patientService.searchPatient(r.get(1)); Optional<Doctor> d = doctorService.findById(r.get(2)); if (p.isPresent() && d.isPresent()) { Appointment a = new Appointment(r.get(0), p.get(), d.get(), DateUtil.parseDate(r.get(3)), r.get(5)); a.setStatus(AppointmentStatus.valueOf(r.get(4))); appointmentService.save(a); } }
    }
    public void saveCsvData() {
        CSVUtil.writeCsv(Constants.PATIENT_CSV, "id,name,age,phone,email,bloodGroup", patientService.listPatients().stream().map(p -> CSVUtil.join(p.getId(), p.getName(), p.getAge(), p.getPhone(), p.getEmail(), p.getBloodGroup())).collect(Collectors.toList()));
        CSVUtil.writeCsv(Constants.DOCTOR_CSV, "id,name,age,phone,email,specialization,consultationFee", doctorService.listDoctors().stream().map(d -> CSVUtil.join(d.getId(), d.getName(), d.getAge(), d.getPhone(), d.getEmail(), d.getSpecialization(), d.getConsultationFee())).collect(Collectors.toList()));
        CSVUtil.writeCsv(Constants.APPOINTMENT_CSV, "id,patientId,doctorId,appointmentDate,status,reason", appointmentService.listAppointments().stream().map(a -> CSVUtil.join(a.getId(), a.getPatient().getId(), a.getDoctor().getId(), DateUtil.formatDate(a.getAppointmentDate()), a.getStatus(), a.getReason())).collect(Collectors.toList()));
    }
    public void saveSnapshot() { SerializationUtil.save(Constants.SERIALIZED_FILE, new MediTrackSnapshot(patientService.listPatients(), doctorService.listDoctors(), appointmentService.listAppointments())); }
    public Optional<MediTrackSnapshot> loadSnapshot() { return SerializationUtil.load(Constants.SERIALIZED_FILE, MediTrackSnapshot.class); }
    public static class MediTrackSnapshot implements Serializable {
        private static final long serialVersionUID = 1L;
        private final List<Patient> patients; private final List<Doctor> doctors; private final List<Appointment> appointments;
        public MediTrackSnapshot(List<Patient> patients, List<Doctor> doctors, List<Appointment> appointments) { this.patients = patients; this.doctors = doctors; this.appointments = appointments; }
        public List<Patient> getPatients() { return patients; }
        public List<Doctor> getDoctors() { return doctors; }
        public List<Appointment> getAppointments() { return appointments; }
    }
}
