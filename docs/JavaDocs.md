# JavaDocs Guide

Generate JavaDocs after installing a JDK that exposes `javadoc` on PATH:

```powershell
$files = Get-ChildItem -Recurse src\main\java -Filter *.java | ForEach-Object FullName
javadoc -d docs\javadoc $files
```

Key APIs: `MedicalEntity`, `Person`, `Patient`, `Doctor`, `Appointment`, `Bill`, `SeniorCitizenBill`, `DataStore<T>`, `PatientService`, `DoctorService`, and `AppointmentService`.
