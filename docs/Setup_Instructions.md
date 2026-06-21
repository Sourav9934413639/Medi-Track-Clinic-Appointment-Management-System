# Setup Instructions

1. Install JDK 8 or later.
2. Add the JDK `bin` folder to `PATH`.
3. Verify setup:

```powershell
java -version
javac -version
```

4. Compile MediTrack:

```powershell
$files = Get-ChildItem -Recurse src\main\java,src\test\java -Filter *.java | ForEach-Object FullName
javac -d out $files
```

5. Run the app:

```powershell
java -cp out com.airtribe.meditrack.Main --loadData
```

6. Run manual tests:

```powershell
java -cp out com.airtribe.meditrack.test.TestRunner
```

Screenshot suggestions for submission: capture `java -version`, `javac -version`, successful compilation, app menu, and test output.
