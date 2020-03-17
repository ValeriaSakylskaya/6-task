package Models.Ship;

public class Ship {
    private long shipId;
    private int loadCapacity;


    public Ship(long Id, int loadCapacity) {
        this.shipId = Id;
        this.loadCapacity = loadCapacity;
    }


    public int getLoadCapacity() {
        return loadCapacity;
    }

    public long getShipId() {
        return shipId;
    }
}
