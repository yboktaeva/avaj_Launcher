package yuboktae;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import yuboktae.factory.AircraftFactory;
import yuboktae.models.Coordinates;
import yuboktae.observer.Flyable;
import yuboktae.observer.WeatherTower;
import yuboktae.singleton.Logger;


/**
* The "main" method starts the program.
* The first command line argument is the input file name
*/
public class Simulator {
    private static int simulationNumber;
    private WeatherTower weatherTower;
    private Logger logger = null;
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
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private void parseAndProcessFile(String filePath) throws IOException {
        this.weatherTower = new WeatherTower();
        this.logger = Logger.getLogger("simulation.txt");
        try(Scanner readFromFile = new Scanner(new File(filePath))) {
            try {
                if (!readFromFile.hasNextLine()) {
                    throw new IllegalArgumentException("Input file is empty");
                }
                String firstLine = readFromFile.nextLine().trim();
                simulationNumber = Integer.parseInt(firstLine);
                if (simulationNumber < 0) {
                    throw new IllegalArgumentException("First line must be a positive integer");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("First line must be a valid positive integer");
            }
            while(readFromFile.hasNextLine()) {
                String line = readFromFile.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] subStrings = line.split(" ");
                if (subStrings.length != 5) {
                    throw new IllegalArgumentException("Error: Invalid input format");
                }
                try {
                    String type = subStrings[0];
                    String name = subStrings[1];
                    int longitude = Integer.parseInt(subStrings[2]);
                    int latitude = Integer.parseInt(subStrings[3]);
                    int height = Integer.parseInt(subStrings[4]);

                    Coordinates coordinates = new Coordinates(longitude, latitude, height);
                    Flyable aircraft =  AircraftFactory.newAircraft(type, name, coordinates);
                    aircraft.registerTower(this.weatherTower);
                    this.weatherTower.register(aircraft);
                    this.logger.log(String.format("Tower says: %s#%s() registered to weather tower.",
                        subStrings[0],
                        name
                        ));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid number format");
                    }
                }
            while (simulationNumber-- > 0) {
                this.weatherTower.changeWeather();
            }
            Logger.getLogger("simulation.txt").close();
        }
    }

    // private static Flyable getAircraft(String[] subStrings) {
    //     String type = subStrings[0];
    //     String name = subStrings[1];
    //     int longitude = Integer.parseInt(subStrings[2]);
    //     int latitude = Integer.parseInt(subStrings[3]);
    //     int height = Integer.parseInt(subStrings[4]);

    //     Coordinates coordinates = new Coordinates(longitude, latitude, height);
    //     AircraftFactory factory = AircraftFactory.getAircraftFactory();
    //     return factory.newAircraft(type, name, coordinates);
    // }
}