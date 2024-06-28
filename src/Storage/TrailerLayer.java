package Storage;

import Enumerations.Configuration;
import Utilities.Stack;

public class TrailerLayer extends Stack<Pallet> {
    private final Pallet[][] palletLayer1;
    private final Pallet[][] palletLayer2;
    private int width = Configuration.INSTANCE.trailerStackWidth;
    private int depth = Configuration.INSTANCE.trailerStackDepth;

    public TrailerLayer() {
        palletLayer1 = new Pallet[width][depth];
        palletLayer2 = new Pallet[width][depth];
    }

    public Pallet[][] getPalletLayer1() {
        return palletLayer1;
    }

    public Pallet[][] getPalletLayer2() {
        return palletLayer2;
    }
}
