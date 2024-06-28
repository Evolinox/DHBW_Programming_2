package Storage;

import BottlingPlant.Label;
import Enumerations.Configuration;
import Enumerations.Locator;

public class Box {
    private final int boxCapacity = Configuration.INSTANCE.boxWidth * Configuration.INSTANCE.boxHeight;
    private final Label label;
    private Bottle[] storageArea;
    private int nextIndex;

    public Box() {
        storageArea = new Bottle[boxCapacity];
        label = new Label(Locator.BOX, 9);
        nextIndex = 0;
    }

    public void addBottle(Bottle bottle) {
        storageArea[nextIndex++] = bottle;
    }

    public boolean isFull() {
        return nextIndex >= boxCapacity;
    }
}
