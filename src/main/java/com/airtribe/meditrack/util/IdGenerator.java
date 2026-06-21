package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public final class IdGenerator {
    private static final IdGenerator EAGER = new IdGenerator();
    private static final AtomicInteger PATIENTS;
    private static final AtomicInteger DOCTORS;
    private static final AtomicInteger APPOINTMENTS;
    private static final AtomicInteger BILLS;
    static { PATIENTS = new AtomicInteger(1000); DOCTORS = new AtomicInteger(1000); APPOINTMENTS = new AtomicInteger(1000); BILLS = new AtomicInteger(1000); }
    private IdGenerator() { }
    public static IdGenerator eagerInstance() { return EAGER; }
    public static IdGenerator lazyInstance() { return Holder.INSTANCE; }
    private static class Holder { private static final IdGenerator INSTANCE = new IdGenerator(); }
    public String nextPatientId() { return "P" + PATIENTS.incrementAndGet(); }
    public String nextDoctorId() { return "D" + DOCTORS.incrementAndGet(); }
    public String nextAppointmentId() { return "A" + APPOINTMENTS.incrementAndGet(); }
    public String nextBillId() { return "B" + BILLS.incrementAndGet(); }
}
