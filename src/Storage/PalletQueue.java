package Storage;

import java.util.LinkedList;
import java.util.Queue;

public class PalletQueue {
    private final Queue<Pallet> palletQueue;
    public PalletQueue() {
        palletQueue = new LinkedList<>();
    }
    public Pallet dequeue() {
        return palletQueue.poll();
    }
    public void enqueue(Pallet pallet) {
        palletQueue.add(pallet);
    }
}
