package yuboktae.observer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Tower {
    private final List <Flyable> observers = new ArrayList<>();
    private final FileWriter fileWriter;

    public Tower() throws IOException {
        File file = new File("simulation.txt");
        file.createNewFile();
        fileWriter = new FileWriter(file, true);
    }

    protected void writeToFile(String log) {
        try {
            fileWriter.write(log + System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void register(Flyable p_flyable) {
        if (!observers.contains(p_flyable)) {
            observers.add(p_flyable);
        }
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
    }
    
    protected void conditionChanged() {
        for (Flyable observer : observers) {
            observer.updateConditions();
        }
    }
}
