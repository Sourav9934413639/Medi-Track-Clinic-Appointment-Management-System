package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.SeniorCitizenBill;
import com.airtribe.meditrack.util.IdGenerator;

public final class BillFactory {
    private BillFactory() { }
    public static Bill createBill(Appointment appointment) {
        if (appointment.getPatient().getAge() >= 60) return new SeniorCitizenBill(IdGenerator.lazyInstance().nextBillId(), appointment, appointment.getDoctor().getConsultationFee(), new SeniorCitizenBillingStrategy());
        return new Bill(IdGenerator.lazyInstance().nextBillId(), appointment, appointment.getDoctor().getConsultationFee(), new StandardBillingStrategy());
    }
}
