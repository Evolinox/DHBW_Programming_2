package Storage;

import Utilities.Stack;
import Enumerations.Configuration;

public class Trailer {
    private final Stack<Pallet>[][] trailer;
    private int width = Configuration.INSTANCE.trailerStackWidth;
    private int height = Configuration.INSTANCE.trailerStackHeight;
    private int depth = Configuration.INSTANCE.trailerStackDepth;
    private int currentWidth = 0;
    private int currentDepth = 0;

    private int lauf = 0;

    public Trailer() {
        trailer = new Stack[width][depth];
        for (int x = 0; x < width; x++) {
            for (int z = 0; z < depth; z++) {
                trailer[x][z] = new Stack<>(height);
            }
        }
    }

    public void push(Pallet pallet) {
        trailer[currentWidth][currentDepth].push(pallet);
        currentDepth++;
        if (currentDepth >= depth && lauf == 0) {
            currentDepth = 0;
            lauf++;
        } else if (currentDepth >= depth && lauf == 1) {
            currentDepth = 0;
            currentWidth++;
            lauf++;
        } else if (currentDepth >= depth && lauf == 2) {
            currentDepth = 0;
            lauf++;
        }
    }
}
