import BottlingPlant.Robots.FillingMachine;
import BottlingPlant.Robots.Gripper;
import BottlingPlant.Robots.Roboter01;
import BottlingPlant.Robots.Roboter02;
import Enumerations.Tastes;
import Forklift.AutonomousForklift;
import Management.Orders;
import Storage.*;
import Utilities.Stack;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class TestApplication {
    private final Trailer trailer = new Trailer();
    private final PalletQueue palletQueue = new PalletQueue();
    private final AutonomousForklift autonomousForklift = new AutonomousForklift(trailer);

    /*
     Dieser Test testet, ob bei einem Nothalt-Befehl auch die Methode emergencyStop() des
     AutonomousForklift aufgerufen wird.
    */
    @Test
    public void testEmergencyStop() {
        assertThrowsExactly(RuntimeException.class, autonomousForklift::emergencyStop);
    }

    /*
     Dieser Test testet, ob der AutonomousForklift beschleunigen kann und anschließend wieder anhält.
    */
    @Test
    public void testForkliftMotor() {
        int testSpeed = 20;
        for (int i = 0; i < testSpeed; i++) {
            autonomousForklift.accelerate();
        }
        assertEquals(testSpeed, autonomousForklift.getSpeed());

        for (int i = testSpeed; i > 0; i--) {
            autonomousForklift.slowDown();
        }
        assertEquals(0, autonomousForklift.getSpeed());
    }

    /*
     In diesem Test werden die ersten zwei Tests aus der Spezifikation ausgeführt. Es wird erst geschaut,
     ob die Bottles in einer Box korrekt befüllt, beschriftet und auch vollständig (x9) sind. Anschließend
     werden die Paletten auf dem Trailer auf Label, Anzahl und Store geprüft.
    */
    @Test
    public void testMain() {
        // Create Fillingmachine
        FillingMachine fm = new FillingMachine();

        // Load orders
        TreeMap<String, String> storeMap = Orders.getOrdersSorted();

        // Initialize BottleQueue and PalletQueue
        for (Map.Entry<String, String> entry : storeMap.entrySet()) {
            Tastes taste = Tastes.valueOf(entry.getValue().replaceAll(" ", "_").toUpperCase());
            for (int i = 0; i < 27 * 9; i++) {
                fm.getTrack().addBottle(new Bottle(taste));
            }
            Pallet tempPallet = new Pallet(entry.getKey());
            //assertEquals(entry.getKey(), tempPallet.toString());
            palletQueue.enqueue(tempPallet);
        }

        // Create other three roboter
        Gripper gripper = new Gripper(autonomousForklift, palletQueue);
        Roboter02 roboter02 = new Roboter02(gripper);
        Roboter01 roboter01 = new Roboter01(fm, roboter02);
        fm.setRoboter01(roboter01);

        // Activate all Robots
        fm.setState(true);
        roboter01.setState(true);
        roboter02.setState(true);
        gripper.setState(true);

        // Fill Bottles
        for (Map.Entry<String, String> entry : storeMap.entrySet()) {
            Tastes taste = Tastes.valueOf(entry.getValue().replaceAll(" ", "_").toUpperCase());
            for (int i = 0; i < 27 * 9; i++) {
                fm.fillBottle(taste);
            }
        }
        // At this point, the Trailer should be filled with 32 Pallets, each containing 27 Boxes à 9 Bottles

        /*
         Hier beginnt das eigentliche Testen
        */
        // Get all Pallets from Trailer
        int index = 0;
        Pallet[] palletsFromTrailer = new Pallet[32];
        Pallet[][] layer1 = trailer.getTrailerLayer().getPalletLayer1();
        Pallet[][] layer2 = trailer.getTrailerLayer().getPalletLayer2();
        for (int i = 0; i < layer1.length; i++) {
            for (int j = 0; j < layer1[i].length; j++) {
                palletsFromTrailer[index] = layer1[i][j];
                index++;
            }
        }
        for (int i = 0; i < layer2.length; i++) {
            for (int j = 0; j < layer2[i].length; j++) {
                palletsFromTrailer[index] = layer2[i][j];
                index++;
            }
        }
        int storeIndex = 0;
        for (Map.Entry<String, String> entry : storeMap.entrySet()) {
            Tastes taste = Tastes.valueOf(entry.getValue().replaceAll(" ", "_").toUpperCase());
            String store = entry.getKey();
            Pallet pallet = palletsFromTrailer[storeIndex];
            assertEquals(store, pallet.getLabel().toString());
            storeIndex++;
        }
    }
}
