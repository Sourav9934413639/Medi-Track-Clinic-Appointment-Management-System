package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.ConsoleAppointmentObserver;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.service.PersistenceService;
import com.airtribe.meditrack.service.billing.BillFactory;
import com.airtribe.meditrack.util.AIHelper;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final PatientService patientService = new PatientService(new DataStore<>());
    private final DoctorService doctorService = new DoctorService(new DataStore<>());
    private final AppointmentService appointmentService = new AppointmentService(new DataStore<>());
    private final PersistenceService persistenceService = new PersistenceService(patientService, doctorService, appointmentService);
    private final Scanner scanner = new Scanner(System.in);

    public Main() { appointmentService.addObserver(new ConsoleAppointmentObserver()); }
    public static void main(String[] args) { Main app = new Main(); if (Arrays.asList(args).contains("--loadData")) { app.persistenceService.loadCsvData(); System.out.println("Loaded persisted CSV data."); } app.run(); }
    private void run() { seedDefaultsWhenEmpty(); boolean running = true; while (running) { printMenu(); String c = scanner.nextLine().trim(); try { switch (c) { case "1": addPatient(); break; case "2": addDoctor(); break; case "3": createAppointment(); break; case "4": cancelAppointment(); break; case "5": listAll(); break; case "6": search(); break; case "7": generateBill(); break; case "8": showAnalytics(); break; case "9": persistenceService.saveCsvData(); persistenceService.saveSnapshot(); System.out.println("Data saved."); break; case "0": running = false; break; default: System.out.println("Invalid menu option."); } } catch (RuntimeException ex) { System.out.println("Error: " + ex.getMessage()); } } System.out.println("Thank you for using " + Constants.APP_NAME + "."); }
    private void printMenu() { System.out.println("\n=== " + Constants.APP_NAME + " ==="); System.out.println("1. Add patient"); System.out.println("2. Add doctor"); System.out.println("3. Create appointment"); System.out.println("4. Cancel appointment"); System.out.println("5. View all records"); System.out.println("6. Search"); System.out.println("7. Generate bill"); System.out.println("8. Analytics"); System.out.println("9. Save data"); System.out.println("0. Exit"); System.out.print("Choice: "); }
    private void addPatient() { System.out.print("Name: "); String name = scanner.nextLine(); System.out.print("Age: "); int age = Integer.parseInt(scanner.nextLine()); System.out.print("Phone: "); String phone = scanner.nextLine(); System.out.print("Email: "); String email = scanner.nextLine(); System.out.print("Blood group: "); String blood = scanner.nextLine(); System.out.println(patientService.addPatient(name, age, phone, email, blood)); }
    private void addDoctor() { System.out.print("Name: "); String name = scanner.nextLine(); System.out.print("Age: "); int age = Integer.parseInt(scanner.nextLine()); System.out.print("Phone: "); String phone = scanner.nextLine(); System.out.print("Email: "); String email = scanner.nextLine(); System.out.print("Specialization " + Arrays.toString(Specialization.values()) + ": "); Specialization s = Specialization.valueOf(scanner.nextLine().trim().toUpperCase()); System.out.print("Consultation fee: "); BigDecimal fee = new BigDecimal(scanner.nextLine()); System.out.println(doctorService.addDoctor(name, age, phone, email, s, fee)); }
    private void createAppointment() { System.out.print("Patient ID: "); Patient p = patientService.searchPatient(scanner.nextLine()).orElseThrow(() -> new InvalidDataException("patient not found")); System.out.print("Symptoms/reason: "); String reason = scanner.nextLine(); System.out.println("Recommended specialization: " + AIHelper.recommendSpecialization(reason)); System.out.print("Doctor ID: "); Doctor d = doctorService.findById(scanner.nextLine()).orElseThrow(() -> new InvalidDataException("doctor not found")); System.out.print("Date (yyyy-MM-dd): "); LocalDate date = DateUtil.parseDate(scanner.nextLine()); System.out.println(appointmentService.createAppointment(p, d, date, reason)); }
    private void cancelAppointment() { System.out.print("Appointment ID: "); System.out.println(appointmentService.cancelAppointment(scanner.nextLine())); }
    private void listAll() { System.out.println("Patients:"); patientService.listPatients().forEach(System.out::println); System.out.println("Doctors:"); doctorService.listDoctors().forEach(System.out::println); System.out.println("Appointments:"); appointmentService.listAppointments().forEach(System.out::println); }
    private void search() { System.out.print("Search text: "); String q = scanner.nextLine(); System.out.println("Matching patients:"); patientService.searchByName(q).forEach(System.out::println); System.out.println("Matching doctors:"); doctorService.searchByName(q).forEach(System.out::println); }
    private void generateBill() { System.out.print("Appointment ID: "); Appointment a = appointmentService.findByIdOrThrow(scanner.nextLine()); Bill bill = BillFactory.createBill(a); System.out.println(bill.summarize()); System.out.println(a.getPatient().paymentMessage(a.getPatient().calculatePayableAmount(bill))); }
    private void showAnalytics() { System.out.println("Average doctor fee: " + doctorService.averageConsultationFee()); System.out.println("Appointments per doctor: " + appointmentService.appointmentsPerDoctor()); }
    private void seedDefaultsWhenEmpty() { if (doctorService.listDoctors().isEmpty()) doctorService.addDoctor("Dr Kavya Iyer", 34, "9988711111", "kavya@example.com", Specialization.GENERAL_MEDICINE, new BigDecimal("500")); }
}
