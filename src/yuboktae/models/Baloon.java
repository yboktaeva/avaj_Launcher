package yuboktae.models;


public class Baloon extends Aircraft {
    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        if (this.coordinates.getHeight() == 0) {
            System.out.println(this.name + " landing");
        }
        System.out.println("Baloon is updating its conditions.");
    }
}
