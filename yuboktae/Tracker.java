package yuboktae;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


 /*Not in UML */
public class Tracker {
    private static File newFile;
    private static PrintWriter writer;

    public Tracker()  {
        try {
            newFile = new File("simulation.txt");
            FileWriter fw = new FileWriter(newFile, true);
            writer = new PrintWriter(fw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void log(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        }
    }

    public static void closeFile() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }
}