package com.airtribe.meditrack.interfaces;

import com.airtribe.meditrack.entity.Bill;
import java.math.BigDecimal;

public interface Payable {
    BigDecimal calculatePayableAmount(Bill bill);
    default String paymentMessage(BigDecimal amount) { return "Amount payable: " + amount; }
}
