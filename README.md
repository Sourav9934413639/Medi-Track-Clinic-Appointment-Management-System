# MediTrack

MediTrack is a console-based Java healthcare appointment and billing system for the Airtribe Java assignment.

## Features

- Encapsulation, inheritance, polymorphism, abstraction, interfaces
- Deep cloning, immutable `BillSummary`, enums, static initialization
- Collections, generics, comparators, iterators, equals/hashCode
- Custom exceptions, exception chaining, try-with-resources
- CSV persistence and Java serialization helpers
- Threads, synchronization, `AtomicInteger`, `TimerTask`
- Singleton, Factory, Strategy, Template-style service flow, Observer
- Java streams, lambdas, and a manual `TestRunner`

## Compile

```powershell
$files = Get-ChildItem -Recurse src\main\java,src\test\java -Filter *.java | ForEach-Object FullName
javac -d out $files
```

## Run

```powershell
java -cp out com.airtribe.meditrack.Main --loadData
```

## Test

```powershell
java -cp out com.airtribe.meditrack.test.TestRunner
```

Java package note: the assignment mentions an `interface` package, but `interface` is a Java keyword, so this project uses `interfaces`.
