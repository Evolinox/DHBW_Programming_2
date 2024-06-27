package Interfaces;

import Storage.Pallet;

public interface IAutonomousForklift {
    public void start();
    public void accelerate();
    public void slowDown();
    public void turnLeft(double degree);
    public void turnRight(double degree);
    public void stop();
    public void emergencyStop();
    public void moveForksUp();
    public void moveForksDown();
    public void take(Pallet pallet);
    public void releasePallet();
}
