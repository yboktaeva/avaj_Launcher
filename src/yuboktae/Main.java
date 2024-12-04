package yuboktae;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


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
            System.out.println(line);
        }
        readFromFile.close();
    }
}