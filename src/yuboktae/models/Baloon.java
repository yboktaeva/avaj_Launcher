package yuboktae.models;


public class Baloon extends Aircraft {
    
    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.Coordinate = p_coordinate;
    }

    public void updateConditions(){
        
    }
}
