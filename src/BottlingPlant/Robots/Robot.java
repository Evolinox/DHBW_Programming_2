package BottlingPlant.Robots;

import BottlingPlant.Track;

public abstract class Robot {
    private final Track track;
    // Attributes
    private boolean isActivated = false;

    // Constructor
    public Robot() {
        track = new Track();
    }

    public boolean getState() {
        return isActivated;
    }

    // Methods
    public void setState(boolean state) {
        isActivated = state;
    }

    public Track getTrack() {
        return track;
    }
}
