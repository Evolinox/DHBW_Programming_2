package Enumerations;

public enum Configuration {
    INSTANCE;
    public final int tankDimensionsX = 50;
    public final int tankDimensionsY = 50;
    public final int tankDimensionsZ = 50;
    public final int boxWidth = 3;
    public final int boxHeight = 3;
    public final int trackBoxInitialAmount = 864;
    public final int trackBottleInitialAmount = 0;
    public final int maxForkliftDriveSpeed = 22;
    public final int maxForkliftForkHeight = 10;
    // Pallet Stack
    public final int palletStackWidth = 3;
    public final int palletStackHeight = 3;
    public final int palletStackDepth = 3;
    // Trailer Stack
    public final int trailerStackWidth = 2;
    public final int trailerStackHeight = 2;
    public final int trailerStackDepth = 8;
    private int lastSerialNumber = 0;

    public int generateNextSerialNumber() {
        lastSerialNumber++;
        return lastSerialNumber;
    }
}
