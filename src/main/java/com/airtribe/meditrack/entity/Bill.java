package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.service.billing.BillingStrategy;
import com.airtribe.meditrack.service.billing.StandardBillingStrategy;
import com.airtribe.meditrack.util.Validator;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Bill extends MedicalEntity {
    private static final long serialVersionUID = 1L;
    private final Appointment appointment;
    private final BigDecimal baseAmount;
    private BillingStrategy billingStrategy;

    public Bill(String id, Appointment appointment) { this(id, appointment, appointment.getDoctor().getConsultationFee(), new StandardBillingStrategy()); }
    public Bill(String id, Appointment appointment, BigDecimal baseAmount, BillingStrategy billingStrategy) { super(id); this.appointment = Validator.requireNonNull(appointment, "appointment"); Validator.requirePositive(baseAmount, "base amount"); this.baseAmount = baseAmount; this.billingStrategy = Validator.requireNonNull(billingStrategy, "billing strategy"); }
    public Appointment getAppointment() { return appointment; }
    public BigDecimal getBaseAmount() { return baseAmount; }
    public BillingStrategy getBillingStrategy() { return billingStrategy; }
    public void setBillingStrategy(BillingStrategy billingStrategy) { this.billingStrategy = Validator.requireNonNull(billingStrategy, "billing strategy"); }
    public BigDecimal generateBill() { return billingStrategy.calculate(this).setScale(2, RoundingMode.HALF_UP); }
    public BigDecimal calculateTax() { return baseAmount.multiply(Constants.TAX_RATE).setScale(2, RoundingMode.HALF_UP); }
    public BillSummary summarize() { return new BillSummary(getId(), appointment.getPatient().getName(), baseAmount, calculateTax(), generateBill()); }
    @Override public String getDisplayName() { return "Bill " + getId() + " for " + appointment.getPatient().getName(); }
    @Override public String toString() { return summarize().toString(); }
}
