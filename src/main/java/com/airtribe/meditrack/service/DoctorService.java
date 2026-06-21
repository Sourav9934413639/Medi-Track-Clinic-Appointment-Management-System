package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorService implements Searchable<Doctor> {
    private final DataStore<Doctor> doctors;
    public DoctorService(DataStore<Doctor> doctors) { this.doctors = doctors; }
    public Doctor addDoctor(String name, int age, String phone, String email, Specialization specialization, BigDecimal fee) { Doctor d = new Doctor(IdGenerator.lazyInstance().nextDoctorId(), name, age, phone, email, specialization, fee); doctors.save(d); return d; }
    public void save(Doctor doctor) { doctors.save(doctor); }
    public Optional<Doctor> findById(String id) { return doctors.findById(id); }
    public List<Doctor> listDoctors() { return doctors.sorted(Comparator.comparing(Doctor::getName)); }
    public boolean deleteDoctor(String id) { return doctors.delete(id); }
    @Override public List<Doctor> searchByName(String name) { return doctors.findAll().stream().filter(d -> matches(d.getName(), name)).collect(Collectors.toList()); }
    public List<Doctor> searchBySpecialization(Specialization specialization) { return doctors.findAll().stream().filter(d -> d.getSpecialization() == specialization).collect(Collectors.toList()); }
    public double averageConsultationFee() { return doctors.findAll().stream().map(Doctor::getConsultationFee).mapToDouble(BigDecimal::doubleValue).average().orElse(0.0); }
}
