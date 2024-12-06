package yuboktae.factory;

import yuboktae.models.*;
import yuboktae.observer.Flyable;


public class AircraftFactory {
    private static AircraftFactory instance;
    private static long counter = 0;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
        }
        return instance;
    }

    private static long generateId() {
        return ++counter;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        long uniqueId = generateId();
        Flyable aircraft;
        switch (p_type.toLowerCase()) {
            case "baloon" -> aircraft = new Baloon(uniqueId, p_name, p_coordinates);
            case "jetplane" -> aircraft = new JetPlane(uniqueId, p_name, p_coordinates);
            case "helicopter" -> aircraft =  new Helicopter(uniqueId, p_name, p_coordinates);
            default -> throw new IllegalArgumentException("Unknown aircraft" + p_type);
        }
        System.out.println(p_type + "#" + p_name + "(" + uniqueId + ") was created");
        return aircraft;
    }
}
