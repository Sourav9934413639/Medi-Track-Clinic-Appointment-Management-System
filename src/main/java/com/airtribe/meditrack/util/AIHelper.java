package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Specialization;

public final class AIHelper {
    private AIHelper() { }
    public static Specialization recommendSpecialization(String symptoms) {
        String s = symptoms == null ? "" : symptoms.toLowerCase();
        if (s.contains("skin") || s.contains("rash") || s.contains("acne")) return Specialization.DERMATOLOGY;
        if (s.contains("heart") || s.contains("chest") || s.contains("bp")) return Specialization.CARDIOLOGY;
        if (s.contains("ear") || s.contains("nose") || s.contains("throat")) return Specialization.ENT;
        if (s.contains("child") || s.contains("baby")) return Specialization.PEDIATRICS;
        if (s.contains("bone") || s.contains("joint") || s.contains("fracture")) return Specialization.ORTHOPEDICS;
        if (s.contains("headache") || s.contains("nerve")) return Specialization.NEUROLOGY;
        return Specialization.GENERAL_MEDICINE;
    }
}
