# JVM Report

## Class Loader

The class loader loads compiled `.class` files into JVM memory. MediTrack classes such as `Main`, `PatientService`, and `Appointment` are loaded from the compiled `out` directory.

## Runtime Data Areas

Heap stores objects such as patients, doctors, appointments, and bills. Stack stores method frames, local variables, primitives, and references for each thread. Method Area stores class metadata, static fields, and constants. PC Register tracks the current bytecode instruction for each thread.

## Execution Engine

The execution engine runs bytecode through interpretation and JIT compilation.

## JIT Compiler vs Interpreter

The interpreter starts execution quickly, one bytecode instruction at a time. The JIT compiler optimizes frequently executed code paths into native machine code.

## Write Once, Run Anywhere

Java compiles source into platform-independent bytecode. Any compatible JVM can run that bytecode on Windows, Linux, or macOS.
