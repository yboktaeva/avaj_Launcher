package yuboktae.models;


public class Coordinates {

    private int longitude;
    private int latitude;
    private int height;
    
    public Coordinates(int p_longitude, int p_latitude, int p_height) {
        this.longitude = p_longitude;
        this.latitude = p_latitude;
        this.height = Math.min(Math.max(p_height, 0), 100); // limiter la hauteur entre 0 et 100
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }
}
