package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;
import java.math.BigDecimal;

public class Doctor extends Person {
    private static final long serialVersionUID = 1L;
    private Specialization specialization;
    private BigDecimal consultationFee;

    public Doctor(String id, String name, int age, String phone, String email, Specialization specialization, BigDecimal consultationFee) {
        super(id, name, age, phone, email);
        this.specialization = specialization;
        setConsultationFee(consultationFee);
    }

    public Specialization getSpecialization() { return specialization; }
    public void setSpecialization(Specialization specialization) { this.specialization = specialization; }
    public BigDecimal getConsultationFee() { return consultationFee; }
    public final void setConsultationFee(BigDecimal consultationFee) { Validator.requirePositive(consultationFee, "consultation fee"); this.consultationFee = consultationFee; }
    @Override public String toString() { return "Doctor{id='" + getId() + "', name='" + getName() + "', specialization=" + specialization + ", fee=" + consultationFee + "}"; }
}
