package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.entity.Bill;
import java.io.Serializable;
import java.math.BigDecimal;

public interface BillingStrategy extends Serializable {
    BigDecimal calculate(Bill bill);
}
