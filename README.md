# Avaj Launcher project
## Overview
This project implements a minimal aircraft simulation program based on a given UML class diagram. It uses Object-Oriented principles and design patterns such as Observer, Singleton, and Factory to ensure extensibility and maintainability.

## Features
- Simulates aircraft behavior in response to weather changes.

- Logs detailed messages for aircraft actions, including registration, unregistration, and weather-induced behavior.

- Validates input scenarios and handles errors gracefully using custom exceptions.

## Requirements
- Java version: Latest LTS (e.g., OpenJDK 17+)

- Build tools: None (no external libraries or tools are allowed).

## Project structure
```
.
├── avaj_Launcher.iml
├── LICENSE
├── README.md
├── ressources
│   ├── avaj_uml.png
│   ├── global.puml
│   └── scenario.txt
├── simulation.txt
├── sources.txt
└── yuboktae
    ├── factory
    │   ├── AircraftFactory.class
    │   └── AircraftFactory.java
    ├── InvalidInputException.class
    ├── models
    │   ├── Aircraft.class
    │   ├── Aircraft.java
    │   ├── Baloon.class
    │   ├── Baloon.java
    │   ├── Coordinates.class
    │   ├── Coordinates.java
    │   ├── Helicopter.class
    │   ├── Helicopter.java
    │   ├── JetPlane.class
    │   └── JetPlane.java
    ├── observer
    │   ├── Flyable.class
    │   ├── Flyable.java
    │   ├── Tower.class
    │   ├── Tower.java
    │   ├── WeatherTower.class
    │   └── WeatherTower.java
    ├── SimulationException.class
    ├── SimulationException.java
    ├── Simulator.class
    ├── Simulator.java
    ├── singleton
    │   ├── WeatherProvider.class
    │   └── WeatherProvider.java
    ├── Tracker.class
    └── Tracker.java

6 directories, 35 files
```
## How to compile and run
### Compilation
In the root directory of your project, compile the program with:
```
find * -name "*.java" > sources.txt
javac @sources.txt
```
### Execution
Run the program using the following command:
```
java yuboktae.Simulator ressources/scenario.txt
```
### Output
- The simulation generates a file simulation.txt describing the outcome.

## Input format
The `scenario.txt` file should contain:

1. **First line**: A positive integer representing the number of weather changes.

2. **Subsequent lines**: Each line describes an aircraft in the format:

    `TYPE NAME LONGITUDE LATITUDE HEIGHT`
    - **TYPE**: Aircraft type(`Baloon`, `Helicopter`, or `Jetplane`).
    - **NAME**: Aircraft name.
    - **LONGITUDE, LATITUDE, HEIGHT**: Positive integers. Height must be in range 0-100.

### Exemple
```
5
Baloon B1 45 32 12
Helicopter H1 50 25 90
JetPlane J1 10 20 30
```
## Simulation rules
- Coordinates are positive numbers.

- Height is limited to the range 0-100.

- Aircrafts receive unique IDs.

- Aircraft lands and unregisters if height reaches or falls below 0.

- Aircraft logs messages for weather changes and actions.

## Logging format
- **Weather-triggered messages**:
    ```
    TYPE#NAME(UNIQUE_ID): SPECIFIC_MESSAGE
    ```
- **Registration/Unregistration messages: Messages are logged when an aircraft registers or unregisters from WeatherTower.

### Exemple log
```
Baloon#B1(1): It's raining. Let’s enjoy the ground!
Helicopter#H1(2): It's sunny. Perfect for a flight.
JetPlane#J1(3): It's foggy. Can't see a thing!
```
## Error handling
- The program validates input data. If the input is invalid, the program stops execution and prints error messages to the standard output.

- Custom exception, such as `SimulationException`is used to handle errors.

## Bonus points
- **Custom Exceptions**: The project includes `SimulationException` for better error handling.

- **Funny messages**: Aircraft log funny messages for weather changes.

## UML Diagram
Refer to the UML diagram in `ressources/avaj_uml.png` for class structure and relationships.

## License
This project is licensed under the MIT License.

## Author
Yulia Boktaeva



