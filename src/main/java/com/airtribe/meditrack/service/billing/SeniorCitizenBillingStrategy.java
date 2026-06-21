package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Bill;
import java.math.BigDecimal;

public class SeniorCitizenBillingStrategy implements BillingStrategy {
    private static final long serialVersionUID = 1L;
    private static final BigDecimal DISCOUNT = new BigDecimal("0.10");
    @Override public BigDecimal calculate(Bill bill) { BigDecimal base = bill.getBaseAmount().subtract(bill.getBaseAmount().multiply(DISCOUNT)); return base.add(base.multiply(Constants.TAX_RATE)); }
}
