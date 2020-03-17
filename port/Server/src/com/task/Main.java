package com.task;

import Models.Port.Dock;
import Models.Port.Port;
import Models.Ship.ShipClient;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        int shipsCount = 4;
        LinkedList<Dock> docks = new LinkedList<Dock>() {
            {
                this.add(new Dock(1));
                this.add(new Dock(342));
                this.add(new Dock(32));
                this.add(new Dock(343422));
            }
        };

        Port<Dock> port = new Port<>(docks);
        for (int i = 0; i < shipsCount; i++) {
            new ShipClient(port).start();
        }
    }
}


