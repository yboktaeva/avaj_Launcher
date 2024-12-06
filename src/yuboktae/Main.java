package yuboktae;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import yuboktae.factory.AircraftFactory;
import yuboktae.models.Coordinates;
import yuboktae.observer.Flyable;
import yuboktae.observer.Tower;


/**
* The "main" method starts the program.
* The first command line argument is the input file name
*/
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid command line, exactly one argument required");
            System.exit(1);
        }

        String filePath = args[0];
        try {
            parseAndProcessFile(filePath);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void parseAndProcessFile(String filePath) throws FileNotFoundException{
        try(Scanner readFromFile = new Scanner(new File(filePath))) {
            int count;
            try {
                if (!readFromFile.hasNextLine()) {
                    throw new IllegalArgumentException("Input file is empty");
                }
                String firstLine = readFromFile.nextLine().trim();
                count = Integer.parseInt(firstLine);
                if (count <= 0) {
                    throw new IllegalArgumentException("First line must be a positive integer");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("First line must be a valid positive integer");
            }
            System.out.println(count);
            while(readFromFile.hasNextLine()) {
                String line = readFromFile.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(" ");
                if (parts.length != 5) {
                    throw new IllegalArgumentException("Error: Invalid input format");
                }
                try {
                    String type = parts[0];
                    String name = parts[1];
                    int longitude = Integer.parseInt(parts[2]);
                    int latitude = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                
                    Coordinates coordinates = new Coordinates(longitude, latitude, height);
                    AircraftFactory factory = AircraftFactory.getInstance();
                    Flyable aircraft = factory.newAircraft(type, name, coordinates);
                    Tower tower = new Tower();
                    tower.register(aircraft);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format");
                }
            }
        }
    }
}