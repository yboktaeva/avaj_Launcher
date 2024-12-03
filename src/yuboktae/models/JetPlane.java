package yuboktae.models;


public class JetPlane extends Aircraft{
    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.Coordinate = p_coordinate;
    }

    public void updateConditions(){
        
    }
}
