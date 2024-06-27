package Storage;

import BottlingPlant.Label;
import Enumerations.Configuration;
import Enumerations.Locator;
import Enumerations.MouthPiece;
import Enumerations.Tastes;

public class Bottle {

    // inicialise Variables
    private String name;
    private char[] content;
    private final MouthPiece mouthPiece;
    private final double height;
    private final int diameter;
    private final int marginalCapacity;
    private final int weight;
    private boolean isFilled = false;
    private final int serialNumber = Configuration.INSTANCE.generateNextSerialNumber();
    private final Label frontLabel;
    private final Label backLabel;

    // Default Constructor
    // add Spezification
    public Bottle() {
        name = "Lab Gin 2008";
        mouthPiece = MouthPiece.CARNETTE;
        height = 164.5;
        diameter = 86;
        marginalCapacity = 545;
        weight = 400;
        // Content
        this.content = new char[marginalCapacity];
        for (int i=0; i < 500; i++) {
            content[i] = (char)103;
        }
        // Special Values
        isFilled = determineFilledStatus(content);
        // Label
        frontLabel = new Label(Locator.FRONT, Tastes.LONDON_DRY_GIN, serialNumber);
        backLabel = new Label(Locator.BACK, Tastes.LONDON_DRY_GIN, serialNumber);
    }

    public Bottle(Tastes taste) {
        name = "Lab Gin 2008";
        mouthPiece = MouthPiece.CARNETTE;
        height = 164.5;
        diameter = 86;
        marginalCapacity = 545;
        weight = 400;
        // Content
        this.content = new char[marginalCapacity];
        for (int i=0; i < 500; i++) {
            content[i] = (char)103;
        }
        // Special Values
        isFilled = determineFilledStatus(content);
        // Label
        frontLabel = new Label(Locator.FRONT, taste, serialNumber);
        backLabel = new Label(Locator.BACK, taste, serialNumber);
    }

    // Constructor
    public Bottle(String name, char[] content, MouthPiece mouthPiece, double height, int diameter, int marginalCapacity, int weight, Tastes taste) {
        this.name = name;
        this.mouthPiece = mouthPiece;
        this.height = height;
        this.diameter = diameter;
        this.marginalCapacity = marginalCapacity;
        this.weight = weight;
        // Content
        this.content = new char[marginalCapacity];
        // Special Values
        isFilled = determineFilledStatus(content);
        // Label
        frontLabel = new Label(Locator.FRONT, taste, serialNumber);
        backLabel = new Label(Locator.BACK, taste, serialNumber);
    }

    public int getMarginalCapacity() {
        return marginalCapacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(char content) {
        int index = 0;
        if (!isFilled) {
            for (int i = 0; i < marginalCapacity; i++) {
                if (this.content[i] != (char)103) {
                    index = i;
                    break;
                }
            }
            this.content[index] = (char)103;
        } else {
            throw new RuntimeException("Bottle is already full!");
        }
    }

    public int getEmptySpace() {
        int space = 0;
        if (isFilled) {
            return 0;
        } else {
            for (int i = 0; i < marginalCapacity; i++) {
                if (this.content[i] != (char)103) {
                    space++;
                }
            }
        }
        return space;
    }

    // Checks, if a Bottle is filled to its maximum Capacity
    private boolean determineFilledStatus(char[] content) {
        boolean state = true;
        for (int i = 0; i < marginalCapacity; i++) {
            if (this.content[i] != (char) 103) {
                state = false;
                break;
            }
        }
        return state;
    }

    // Check Identity of Bottles
    public boolean equals(Object obj) {
        // Check for Null-Reference
        if(obj == null) {
            return false;
        }
        // Check for Reference to yourself
        if(this == obj) {
            return true;
        }
        // Check for Same SerialNumber (Part of Instance)
        if(obj instanceof Bottle) {
            return this.serialNumber == ((Bottle) obj).serialNumber;
        }
        return false;
    }
}
