package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.util.Validator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patient extends Person implements Cloneable, Payable {
    private static final long serialVersionUID = 1L;
    private String bloodGroup;
    private List<String> medicalHistory;

    public Patient(String id, String name, int age, String phone, String email, String bloodGroup) {
        super(id, name, age, phone, email);
        setBloodGroup(bloodGroup);
        this.medicalHistory = new ArrayList<>();
    }

    public String getBloodGroup() { return bloodGroup; }
    public final void setBloodGroup(String bloodGroup) { Validator.requireText(bloodGroup, "blood group"); this.bloodGroup = bloodGroup.trim(); }
    public List<String> getMedicalHistory() { return Collections.unmodifiableList(medicalHistory); }
    public void addMedicalHistory(String note) { Validator.requireText(note, "medical history note"); medicalHistory.add(note.trim()); }
    @Override public BigDecimal calculatePayableAmount(Bill bill) { return bill.generateBill(); }
    @Override public Patient clone() { try { Patient p = (Patient) super.clone(); p.medicalHistory = new ArrayList<>(medicalHistory); return p; } catch (CloneNotSupportedException ex) { throw new AssertionError(ex); } }
    @Override public String toString() { return "Patient{id='" + getId() + "', name='" + getName() + "', age=" + getAge() + ", bloodGroup='" + bloodGroup + "'}"; }
}
