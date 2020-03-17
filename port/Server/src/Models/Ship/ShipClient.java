package Models.Ship;

import Models.Port.Dock;
import Models.Port.Port;
import com.task.ResourceException;

import java.util.Random;

public class ShipClient extends Thread {
    private Port<Dock> port;
    private Ship ship;

    public ShipClient(Port<Dock> port) {
        this.port = port;
        this.ship = new Ship(this.getId(), 1 + new Random().nextInt(5));
    }

    public void run() {
        Dock dock = null;
        try {
            dock = port.getResources();
            System.out.println("Ship №" + this.getId() + "  gate in full port dock #" + dock.getDockId());
            dock.unload(ship.getLoadCapacity(), ship.getShipId());
        } catch (ResourceException e) {
            System.out.println("Ship #" + this.getId() + "lost->" + e.getMessage());
        } finally {
            if (dock != null) {
                System.out.println("Ship #" + this.getId() + " gate out full ");
                port.returnResources(dock);
            }
        }
    }
}
