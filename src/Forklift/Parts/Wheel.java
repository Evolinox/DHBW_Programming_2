package Forklift.Parts;

public class Wheel {
    private int speed = 0;
    private int turnAngle = 0;
    public Wheel() {

    }
    public void setSpeed(int s) {
        speed = s;
    }
    public int getSpeed() {
        return speed;
    }
    public void setTurnAngle(int a) {
        turnAngle = a;
    }
    public int getTurnAngle() {
        return turnAngle;
    }
}
