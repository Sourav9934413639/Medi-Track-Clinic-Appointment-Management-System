package com.airtribe.meditrack.constants;

import java.math.BigDecimal;

public final class Constants {
    public static final String APP_NAME;
    public static final BigDecimal TAX_RATE;
    public static final String DATA_DIR;
    public static final String PATIENT_CSV;
    public static final String DOCTOR_CSV;
    public static final String APPOINTMENT_CSV;
    public static final String SERIALIZED_FILE;

    static {
        APP_NAME = "MediTrack";
        TAX_RATE = new BigDecimal("0.18");
        DATA_DIR = "data";
        PATIENT_CSV = DATA_DIR + "/patients.csv";
        DOCTOR_CSV = DATA_DIR + "/doctors.csv";
        APPOINTMENT_CSV = DATA_DIR + "/appointments.csv";
        SERIALIZED_FILE = DATA_DIR + "/meditrack.ser";
    }

    private Constants() { }
}
