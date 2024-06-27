package Storage;

import BottlingPlant.Label;
import Enumerations.Configuration;
import Enumerations.Locator;

public class Pallet {
    private final Stack<Box[][]> boxes;
    private final Label label;

    public Pallet(String store) {
        boxes = new Stack<>();
        label = new Label(Locator.PALLET, store);
    }

    public void push(Box box) {
        if (getNextSlot() == null) {
            // Stack is empty, might be because full
            if (boxes.size() >= Configuration.INSTANCE.palletStackHeight) {
                throw new RuntimeException("Stack is already full!");
            }
            // Or because the Stack wasn't initialized
            else {
                Box[][] newStack = new Box[Configuration.INSTANCE.palletStackWidth][Configuration.INSTANCE.palletStackDepth];
                newStack[0][0] = box;
                boxes.push(newStack);
            }
        } else {
            final int[] pos = getNextSlot();
            Box[][] newStack = boxes.getStack();
            newStack[pos[0]][pos[1]] = box;
            boxes.push(newStack);
        }
    }

    public boolean isFull() {
        return boxes.size() >= Configuration.INSTANCE.palletStackHeight;
    }

    private int[] getNextSlot() {
        if (boxes.isEmpty()) {
            return null;
        }
        final Box[][] stack = boxes.getStack();
        for (int x = 0; x < Configuration.INSTANCE.palletStackWidth; x++) {
            for (int y = 0; y < Configuration.INSTANCE.palletStackDepth; y++) {
                if (stack[x][y] == null) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }
}
