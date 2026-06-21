package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientService implements Searchable<Patient> {
    private final DataStore<Patient> patients;
    public PatientService(DataStore<Patient> patients) { this.patients = patients; }
    public Patient addPatient(String name, int age, String phone, String email, String bloodGroup) { Patient p = new Patient(IdGenerator.eagerInstance().nextPatientId(), name, age, phone, email, bloodGroup); patients.save(p); return p; }
    public void save(Patient patient) { patients.save(patient); }
    public Optional<Patient> searchPatient(String id) { return patients.findById(id); }
    public List<Patient> searchPatient(String name, boolean byName) { return byName ? searchByName(name) : patients.findAll(); }
    public List<Patient> searchPatient(int age) { return patients.findAll().stream().filter(p -> p.getAge() == age).collect(Collectors.toList()); }
    public List<Patient> listPatients() { return patients.sorted(Comparator.comparing(Patient::getName)); }
    public boolean deletePatient(String id) { return patients.delete(id); }
    @Override public List<Patient> searchByName(String name) { return patients.findAll().stream().filter(p -> matches(p.getName(), name)).collect(Collectors.toList()); }
}
