package yuboktae.models;


public class Helicopter extends Aircraft{
    
    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.Coordinate = p_coordinate;
    }

    public void updateConditions(){
        
    }
}
