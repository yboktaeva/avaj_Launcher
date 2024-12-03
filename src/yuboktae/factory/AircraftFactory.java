package yuboktae.factory;

import yuboktae.models.*;
import yuboktae.observer.Flyable;


public class AircraftFactory {
    
    public Flyable* newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        switch (p_type.toLowerCase()) {
            case "baloon":
                return new Baloon(p_id, p_name, p_coordinates);
            case "helicopter":
                return new Helicopter(p_id, p_name, p_coordinates);
            case "jetplane":
                return new JetPlane(p_id, p_name, p_coordinates);
        }
    }
}
