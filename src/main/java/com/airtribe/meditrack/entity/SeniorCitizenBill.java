package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.service.billing.BillingStrategy;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SeniorCitizenBill extends Bill {
    private static final long serialVersionUID = 1L;
    public SeniorCitizenBill(String id, Appointment appointment, BigDecimal baseAmount, BillingStrategy billingStrategy) { super(id, appointment, baseAmount, billingStrategy); }
    @Override public BigDecimal generateBill() { return getBillingStrategy().calculate(this).setScale(2, RoundingMode.HALF_UP); }
}
