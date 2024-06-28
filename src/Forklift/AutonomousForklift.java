package Forklift;

import Enumerations.Configuration;
import Forklift.Parts.Chassis;
import Interfaces.IAutonomousForklift;
import Storage.Pallet;
import Storage.Trailer;

public class AutonomousForklift implements IAutonomousForklift {
    private final Trailer trailer;
    private final Chassis chassis;
    private final AIEngine aiEngine2048;
    private final AIEngine aiEngine4096;
    private Pallet currentPallet;
    private boolean isStarted = false;
    private int speed = 0;
    private int forkHeight = 0;

    public AutonomousForklift(Trailer trailer) {
        this.trailer = trailer;
        this.chassis = new Chassis();
        this.aiEngine2048 = new AIEngine(2048);
        this.aiEngine4096 = new AIEngine(4096);
    }

    public void setStarted(boolean state) {
        isStarted = state;
    }

    public boolean getState() {
        return isStarted;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void start() {
        if (!isStarted) {
            isStarted = true;
        } else {
            throw new RuntimeException("Forklift already started!");
        }
    }

    @Override
    public void accelerate() {
        if (speed < Configuration.INSTANCE.maxForkliftDriveSpeed) {
            speed++;
        } else {
            throw new RuntimeException("Forklift can't go any faster");
        }
    }

    @Override
    public void slowDown() {
        if (speed > 0) {
            speed--;
        }
    }

    @Override
    public void turnLeft(double degree) {
        if (5.0 <= degree && degree <= 30.0) {
            // turn left
        } else {
            throw new IllegalArgumentException("Value out of bound!");
        }
    }

    @Override
    public void turnRight(double degree) {
        if (5.0 <= degree && degree <= 30.0) {
            // turn right
        } else {
            throw new IllegalArgumentException("Value out of bound!");
        }
    }

    @Override
    public void stop() {
        speed = 0;
        if (isStarted) {
            isStarted = false;
        } else {
            throw new RuntimeException("Forklift not started!");
        }
    }

    @Override
    public void emergencyStop() {
        speed = 0;
        isStarted = false;
        throw new RuntimeException("Nothalt!");
    }

    @Override
    public void moveForksUp() {
        if (forkHeight <= Configuration.INSTANCE.maxForkliftForkHeight) {
            forkHeight++;
        } else {
            throw new RuntimeException("Fork can't go any higher");
        }
    }

    @Override
    public void moveForksDown() {
        if (forkHeight > 0) {
            forkHeight--;
        } else {
            throw new RuntimeException("Fork can't go any lower");
        }
    }

    @Override
    public void take(Pallet pallet) {
        this.currentPallet = pallet;
        releasePallet();
    }

    @Override
    public void releasePallet() {
        trailer.push(currentPallet);
        currentPallet = null;
    }
}
