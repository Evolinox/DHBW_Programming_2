package BottlingPlant.Robots;

import BottlingPlant.Track;

public abstract class Robot {
    // Attributes
    private boolean isActivated = false;
    private final Track track;

    // Constructor
    public Robot() {
        track = new Track();
    }

    // Methods
    public void setState(boolean state) {
        isActivated = state;
    }

    public boolean getState() {
        return isActivated;
    }

    public Track getTrack() {
        return track;
    }
}
