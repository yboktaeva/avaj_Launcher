package yuboktae;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import yuboktae.factory.AircraftFactory;
import yuboktae.models.Coordinates;
import yuboktae.observer.Flyable;
import yuboktae.observer.WeatherTower;


/**
* The "main" method starts the program.
* The first command line argument is the input file name
*/
public class Simulator {
    private static int simulationNumber;
    private WeatherTower weatherTower;
    private Tracker tracker = new Tracker();

    private Simulator() {}
    
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Invalid command line, exactly one argument required");
            System.exit(1);
        }
        Simulator simulator = new Simulator();

        String filePath = args[0];
        try {
            simulator.parseAndProcessFile(filePath);
        } catch (FileNotFoundException | SimulationException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    
    private void parseAndProcessFile(String filePath) throws SimulationException, FileNotFoundException {
        weatherTower = new WeatherTower();
        try (Scanner readFromFile = new Scanner(new File(filePath))) {
            if (!readFromFile.hasNextLine()) {
                throw new SimulationException("Input file is empty");
            }
            String firstLine = readFromFile.nextLine().trim();
            if (firstLine.isEmpty()) {
                throw new SimulationException("First line cannot be empty");
            }
            simulationNumber = Integer.parseInt(firstLine);
            if (simulationNumber < 0) {
                throw new SimulationException("First line must be a positive integer");
            }
            if (!readFromFile.hasNextLine()) {
                throw new SimulationException("No aircraft data provided.");
            }
            while(readFromFile.hasNextLine()) {
                String line = readFromFile.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] subStrings = line.split(" ");
                if (subStrings.length != 5) {
                    throw new SimulationException("Error: Invalid input format");
                }
                Flyable aircraft = getAircraft(subStrings);
                aircraft.registerTower(weatherTower);
                weatherTower.register(aircraft);
                Tracker.log(String.format("Tower says: %s registered to weather tower.",
                        aircraft.getFullName()
                ));
            }
            readFromFile.close();
            Tracker.log("START OF SIMULATION");
            while (simulationNumber-- > 0) {
                this.weatherTower.changeWeather();
            }
            Tracker.log("END OF SIMULATION");
            Tracker.closeFile();
        } catch (SimulationException e) {
            System.err.println(e.getMessage());
        }
    }

    private static Flyable getAircraft(String[] subStrings) throws SimulationException {
        String type = subStrings[0];
        String name = subStrings[1];
        int longitude = Integer.parseInt(subStrings[2]);
        int latitude = Integer.parseInt(subStrings[3]);
        int height = Integer.parseInt(subStrings[4]);
        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new SimulationException("Coordinates must be a positive integers.");
        }
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        return AircraftFactory.newAircraft(type, name, coordinates);
    }
}