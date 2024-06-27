package BottlingPlant.Robots;

import Forklift.AutonomousForklift;
import Storage.Box;
import Storage.Pallet;
import Storage.PalletQueue;

public class Gripper extends Robot {
    private Pallet pallet;
    private PalletQueue palletQueue;
    private AutonomousForklift autonomousForklift;
    public Gripper(AutonomousForklift autonomousForklift, PalletQueue palletQueue) {
        super();
        this.palletQueue = palletQueue;
        this.pallet = palletQueue.dequeue();
        this.autonomousForklift = autonomousForklift;
    }

    public void addBoxToPallets(Box box) {
        if (super.getState()) {
            // Push Box
            pallet.push(box);
            // Check, if pallet full
            if (pallet.isFull()) {
                // If full, send to autonomousForklift
                autonomousForklift.take(pallet);
                // Get new Pallet
                pallet = palletQueue.dequeue();
            }
        } else {
            throw new RuntimeException("Gripper is not active!");
        }
    }
}
