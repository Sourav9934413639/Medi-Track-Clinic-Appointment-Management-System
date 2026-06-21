package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.entity.Bill;
import java.math.BigDecimal;

public class StandardBillingStrategy implements BillingStrategy {
    private static final long serialVersionUID = 1L;
    @Override public BigDecimal calculate(Bill bill) { return bill.getBaseAmount().add(bill.calculateTax()); }
}
