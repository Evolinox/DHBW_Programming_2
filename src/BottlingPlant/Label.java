package BottlingPlant;

import Enumerations.Locator;
import Enumerations.Tastes;

import java.time.Clock;
import java.util.UUID;

public class Label {
    private final UUID uuid = UUID.randomUUID();
    private final String labelText;
    public Label(Locator type, int bottleCount) {
        if (type != Locator.BOX) {
            throw new IllegalArgumentException("Dieser Konstruktor ist nur für den Typ BOX!");
        } else {
            labelText = uuid + " | Product | " + bottleCount;
        }
    }
    public Label(Locator type, String store) {
        if (type != Locator.PALLET) {
            throw new IllegalArgumentException("Dieser Konstruktor ist nur für den Typ PALLET!");
        } else {
            labelText = store;
        }
    }
    public Label(Locator type, Tastes taste, int serial) {
        Clock clock = Clock.systemDefaultZone();

        switch (type) {
            case FRONT:
                labelText = "Tanqueray | " + taste.toString();
                break;
            case BACK:
                labelText = "Drink responsible" + System.lineSeparator()
                        + clock.instant().getNano() + System.lineSeparator()
                        + "Seq: " + serial;
                break;
            default:
                labelText = "Printing not successful :(";
                break;
        }
    }

    @Override
    public String toString() {
        return labelText;
    }
}
