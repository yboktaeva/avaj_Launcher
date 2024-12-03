package yuboktae.models;


public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinate coordiantes;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.Coordinate = p_coordinate;
    }
}
