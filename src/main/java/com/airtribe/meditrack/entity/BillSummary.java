package com.airtribe.meditrack.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class BillSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String billId;
    private final String patientName;
    private final BigDecimal baseAmount;
    private final BigDecimal taxAmount;
    private final BigDecimal totalAmount;
    private final LocalDateTime generatedAt;

    public BillSummary(String billId, String patientName, BigDecimal baseAmount, BigDecimal taxAmount, BigDecimal totalAmount) {
        this.billId = billId; this.patientName = patientName; this.baseAmount = baseAmount; this.taxAmount = taxAmount; this.totalAmount = totalAmount; this.generatedAt = LocalDateTime.now();
    }

    public String getBillId() { return billId; }
    public String getPatientName() { return patientName; }
    public BigDecimal getBaseAmount() { return baseAmount; }
    public BigDecimal getTaxAmount() { return taxAmount; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    @Override public String toString() { return "BillSummary{billId='" + billId + "', patientName='" + patientName + "', baseAmount=" + baseAmount + ", taxAmount=" + taxAmount + ", totalAmount=" + totalAmount + ", generatedAt=" + generatedAt + "}"; }
}
