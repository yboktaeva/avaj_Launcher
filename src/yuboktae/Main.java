package yuboktae;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import yuboktae.factory.AircraftFactory;
import yuboktae.models.Coordinates;
import yuboktae.observer.Flyable;


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
        Scanner readFromFile = null;
        try {  
            readFromFile = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.print("Error: File not found for \"");
            System.out.println(args[0]+"\"");
            System.exit(1);
        }
        while(readFromFile.hasNextLine()) {
            String line = readFromFile.nextLine();
            String[] parts = line.split(" ");
            if (parts.length != 5) {
                System.err.println("Error: Invalid input format" + line);
                System.exit(1);
            }
            String type = parts[0];
            String name = parts[1];
            int longitude = Integer.parseInt(parts[2]);
            int latitude = Integer.parseInt(parts[3]);
            int height = Integer.parseInt(parts[4]);

            Coordinates coordinates = new Coordinates(longitude, latitude, height);
            AircraftFactory factory = AircraftFactory.getInstance();
            Flyable aircraft = factory.newAircraft(type, name, coordinates);
        }
        readFromFile.close();
    }
}