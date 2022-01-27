# Little Helpers: Useful Tools for (Java) Developers

This repository contains all sources and slides to replay the Tutoring-Session *Little Helpers: Useful Tools for (Java) Developers*.

## Prerequisites

* Unrestricted Internet Access for Maven usage
* Local IDE installation, eg. IntelliJ or Eclipse (optional). You can download IntelliJ CE here: https://www.jetbrains.com/idea/download/
* Java 11+ (might be included in IDE)
* Maven 3+ (might be included in IDE)

## Run 

* The `FileMonitor` can be started with `mvn spring-boot:run` or by starting `LittleHelpersApplication`.
* All other samples are included as Unit tests in `src/test/java`.
* The test can be executed with `mvn test` and in any IDE with JUnit support.
* The `FileMonitor` can also be started from the IDE as a main class.
* The Excel-Apache-POI-Sample is included in `ExcelTransformer`. Results are in `src/test/resources/*_out.xlsx`