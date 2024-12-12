package yuboktae;


public class SimulationException extends Exception {
    public SimulationException(String message) {
        super("Simulation error: " + message);
    }

    public SimulationException(String message, Throwable cause) {
        super("Simulation error: " + message, cause);
    }
}