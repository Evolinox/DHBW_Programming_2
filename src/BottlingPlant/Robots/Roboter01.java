package BottlingPlant.Robots;

import Storage.Bottle;
import Storage.Box;

public class Roboter01 extends Robot {
    private final FillingMachine fillingMachine;
    private final Roboter02 roboter02;
    private Box currentBox;

    // Constructer
    public Roboter01(FillingMachine fillingMachine, Roboter02 roboter02) {
        super();
        this.fillingMachine = fillingMachine;
        this.roboter02 = roboter02;

        // Get new Box from Queue
        this.roboter02.setState(true);
        currentBox = roboter02.getEmptyBoxFromTrack();
        this.roboter02.setState(false);
    }

    public Roboter02 getRoboter02() {
        return roboter02;
    }

    // Gets Bottle from Filling Machine and places it in a Box
    // If the Box is full, it will tell Roboter02, that the Box
    // can be placed in the Storage and also needs a new empty
    // Box
    public void retrieveFilledBottle(Bottle bottle) {
        if (super.getState()) {
            currentBox.addBottle(bottle);
            if (currentBox.isFull()) {
                roboter02.setFullBoxToStorage(currentBox);
                currentBox = roboter02.getEmptyBoxFromTrack();
            }
        } else {
            throw new RuntimeException("Roboter01 is not active!");
        }
    }
}
