package BottlingPlant;

import Enumerations.Configuration;
import Storage.Bottle;
import Storage.Box;

import java.util.LinkedList;
import java.util.Queue;

public class Track {
    private final Queue<Bottle> bottleQueue;
    private final Queue<Box> boxQueue;
    public Track() {
        // Init the Queues
        bottleQueue = new LinkedList<>();
        boxQueue = new LinkedList<>();
        /* Insert the Queues */
        for (int i = 0; i < Configuration.INSTANCE.trackBottleInitialAmount; i++) {
            bottleQueue.add(new Bottle());
        }
        for (int i = 0; i < Configuration.INSTANCE.trackBoxInitialAmount; i++) {
            boxQueue.add(new Box());
        }
    }
    public void addBottle(Bottle bottle) {
        bottleQueue.add(bottle);
    }
    public Bottle getNextBottle() {
        return bottleQueue.poll();
    }
    public Box getNextBox() {
        return boxQueue.poll();
    }
}
