package Storage;

import Enumerations.Configuration;

public class Trailer {
    private final Stack<Pallet[][]> pallets;
    public Trailer() {
        pallets = new Stack<>();
    }
    public boolean isFull() {
        return pallets.size() >= Configuration.INSTANCE.trailerStackHeight;
    }
    public void push(Pallet pallet) {
        if (getNextSlot() == null) {
            // Stack is empty, might be because full
            if (pallets.size() >= Configuration.INSTANCE.trailerStackHeight) {
                throw new RuntimeException("Stack is already full!");
            }
            // Or because the Stack wasn't initialized
            else {
                Pallet[][] newStack = new Pallet[Configuration.INSTANCE.trailerStackWidth][Configuration.INSTANCE.trailerStackDepth];
                newStack[0][0] = pallet;
                pallets.push(newStack);
            }
        } else {
            final int[] pos = getNextSlot();
            Pallet[][] newStack = pallets.getStack();
            newStack[pos[0]][pos[1]] = pallet;
            pallets.push(newStack);
        }
    }
    private int[] getNextSlot() {
        if (pallets.isEmpty()) {
            return null;
        }
        final Pallet[][] stack = pallets.getStack();
        for (int x = 0; x < Configuration.INSTANCE.trailerStackWidth; ++x) {
            for (int y = 0; y < Configuration.INSTANCE.trailerStackDepth; ++y) {
                if (stack[x][y] == null) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }
}
