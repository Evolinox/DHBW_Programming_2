package BottlingPlant.Robots;

import Enumerations.Tastes;
import Storage.Bottle;
import Storage.Tank;

public class FillingMachine extends Robot {

    // Attributes
    private final Tank tank;
    private Roboter01 roboter01;

    // Constructor
    public FillingMachine() {
        super();
        tank = new Tank();
    }

    public void fillBottle(Tastes taste) {
        if (super.getState()) {
            // Get Bottle from Queue
            Bottle bottle = getBottleFromTrack();
            // Get maxCapacity for given Bottle
            int maxCapacity = bottle.getEmptySpace();
            // Fills Bottle to maxCapacity, should ignore the fact, that a bottle might not be empty
            for (int i = 0; i < maxCapacity; i++) {
                bottle.setContent(tank.getGin(taste));
            }
            roboter01.retrieveFilledBottle(bottle);
        } else {
            throw new RuntimeException("FillingMachine is not active!");
        }
    }

    public void fillBottle(Bottle bottle, Tastes taste) {
        if (super.getState()) {
            bottle.setContent(tank.getGin(taste));
        } else {
            throw new RuntimeException("FillingMachine is not active!");
        }
    }

    private Bottle getBottleFromTrack() {
        return super.getTrack().getNextBottle();
    }

    public void setRoboter01(Roboter01 roboter01) {
        this.roboter01 = roboter01;
    }
}

