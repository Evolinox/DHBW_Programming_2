package Forklift.Parts;

public class Chassis {
    private final Wheel[] wheels;
    private final Fork[] forks;
    private final Mast mast;

    public Chassis() {
        // Wheels
        wheels = new Wheel[2];
        wheels[0] = new Wheel();
        wheels[1] = new Wheel();

        // Forks
        forks = new Fork[2];
        forks[0] = new Fork();
        forks[1] = new Fork();

        // Mast
        mast = new Mast();
    }
}
