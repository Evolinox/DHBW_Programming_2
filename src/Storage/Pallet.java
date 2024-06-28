package Storage;

import BottlingPlant.Label;
import Enumerations.Configuration;
import Enumerations.Locator;

public class Pallet {
    private final Stack<Box[][]> boxes;
    private final Label label;
    private int numberOfBoxes = 0;

    public Label getLabel() {
        return label;
    }

    public int getNumBoxes() {
        return numberOfBoxes;
    }

    public Pallet(String store) {
        boxes = new Stack<>();
        label = new Label(Locator.PALLET, store);
    }

    public void push(Box box) {
        if (getNextSlot() == null) {
            // Stack is empty, might be because full
            if (isFull()) {
                throw new RuntimeException("Stack is already full!");
            }
            // Or because the Stack wasn't initialized
            else {
                Box[][] newStack = new Box[Configuration.INSTANCE.palletStackWidth][Configuration.INSTANCE.palletStackDepth];
                newStack[0][0] = box;
                boxes.push(newStack);
                numberOfBoxes++;
            }
        } else {
            final int[] pos = getNextSlot();
            Box[][] newStack = boxes.getStack();
            newStack[pos[0]][pos[1]] = box;
            numberOfBoxes++;
        }
    }

    public boolean isFull() {
        if(boxes.size() < 3) {
            return false;
        }
        return getNextSlot() == null;
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

    public Box getBox() {
        if (numberOfBoxes == 0) {
            throw new RuntimeException("Stack is empty!");
        }

        Box topBox = null;
        for (int x = Configuration.INSTANCE.palletStackWidth - 1; x >= 0; x--) {
            for (int y = Configuration.INSTANCE.palletStackDepth - 1; y >= 0; y--) {
                if (boxes.getStack()[x][y] != null) {
                    topBox = boxes.getStack()[x][y];
                    boxes.getStack()[x][y] = null;
                    numberOfBoxes--;
                    if (x == 0 && y == 0 && numberOfBoxes > 0) {
                        boxes.pop();
                    }
                    return topBox;
                }
            }
        }
        return topBox;
    }
}