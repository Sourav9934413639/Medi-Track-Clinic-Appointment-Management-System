package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.SeniorCitizenBill;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.service.billing.BillFactory;
import com.airtribe.meditrack.util.AIHelper;
import com.airtribe.meditrack.util.DataStore;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TestRunner {
    public static void main(String[] args) { testCrudAndSearch(); testValidation(); testAppointmentAndBilling(); testDeepClone(); testStreamsAndAiHelper(); System.out.println("All manual tests passed."); }
    private static void testCrudAndSearch() { PatientService service = new PatientService(new DataStore<>()); Patient p = service.addPatient("Asha", 29, "9876543210", "asha@example.com", "O+"); assertTrue(service.searchPatient(p.getId()).isPresent(), "patient by id"); assertTrue(service.searchPatient("ash", true).size() == 1, "patient by name"); assertTrue(service.searchPatient(29).size() == 1, "patient by age"); assertTrue(service.deletePatient(p.getId()), "delete patient"); }
    private static void testValidation() { try { new Patient("P1", "", 0, "123", "bad", "O+"); throw new AssertionError("invalid patient should fail"); } catch (InvalidDataException expected) { System.out.println("Validation test passed."); } }
    private static void testAppointmentAndBilling() { Patient p = new Patient("P1", "Rohan", 65, "9876543210", "rohan@example.com", "B+"); Doctor d = new Doctor("D1", "Dr Rao", 38, "9988776655", "rao@example.com", Specialization.CARDIOLOGY, new BigDecimal("1000")); AppointmentService service = new AppointmentService(new DataStore<>()); Appointment a = service.createAppointment(p, d, LocalDate.now().plusDays(1), "chest pain"); service.cancelAppointment(a.getId()); assertTrue(a.getStatus() == AppointmentStatus.CANCELLED, "appointment cancelled"); Bill bill = BillFactory.createBill(a); assertTrue(bill instanceof SeniorCitizenBill, "senior bill subtype"); assertTrue(bill.generateBill().compareTo(new BigDecimal("1062.00")) == 0, "senior bill total"); }
    private static void testDeepClone() { Patient p = new Patient("P2", "Mira", 31, "9876543211", "mira@example.com", "A+"); p.addMedicalHistory("Allergy"); Doctor d = new Doctor("D2", "Dr Sen", 45, "9988776656", "sen@example.com", Specialization.DERMATOLOGY, new BigDecimal("600")); Appointment original = new Appointment("A1", p, d, LocalDate.now(), "rash"); Appointment cloned = original.clone(); cloned.getPatient().addMedicalHistory("Clone-only note"); assertTrue(original.getPatient().getMedicalHistory().size() == 1, "deep clone history"); }
    private static void testStreamsAndAiHelper() { DoctorService ds = new DoctorService(new DataStore<>()); ds.addDoctor("Dr A", 40, "9999999991", "a@example.com", Specialization.ENT, new BigDecimal("500")); ds.addDoctor("Dr B", 41, "9999999992", "b@example.com", Specialization.ENT, new BigDecimal("700")); assertTrue(ds.searchBySpecialization(Specialization.ENT).size() == 2, "stream filter"); assertTrue(ds.averageConsultationFee() == 600.0, "average fee"); assertTrue(AIHelper.recommendSpecialization("skin rash") == Specialization.DERMATOLOGY, "AI helper"); }
    private static void assertTrue(boolean condition, String message) { if (!condition) throw new AssertionError(message); }
}
