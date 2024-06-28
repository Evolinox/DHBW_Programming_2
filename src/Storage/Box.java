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
        label = new Label(Locator.BOX, storageArea.length);
        nextIndex = 0;
    }

    public void addBottle(Bottle bottle) {
        storageArea[nextIndex++] = bottle;
        label.updateBottleCount(Locator.BOX, storageArea.length);
    }

    public int getBottlesCount() {
        return storageArea.length;
    }

    public Bottle[] getBottleArray() {
        return storageArea;
    }

    public boolean isFull() {
        return nextIndex >= boxCapacity;
    }
}
