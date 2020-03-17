package Models.Port;

import java.util.Random;

public class Dock {
    private int dockId;

    public Dock(int dockId) {
        super();
        this.dockId = dockId;
    }

    public int getDockId() {
        return dockId;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public void unload(int capacity, long shipId) {
        try {
            for (int i = 1; i <= capacity; i++) {
                Thread.sleep(new Random().nextInt(500));
                System.out.println("container № " + i + " unload on board ship# " + shipId);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
