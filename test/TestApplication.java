import BottlingPlant.Robots.FillingMachine;
import BottlingPlant.Robots.Gripper;
import BottlingPlant.Robots.Roboter01;
import BottlingPlant.Robots.Roboter02;
import Enumerations.GPUModel;
import Enumerations.Tastes;
import Forklift.AutonomousForklift;
import Management.Orders;
import Storage.*;
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
     Test swap-functionality for AIEngine
     */
    @Test
    public void testGpuSwap() {
        // AutonomousForklift uses Performance GPU on standard
        assertEquals(autonomousForklift.getAiEngine().getGpuModel(), GPUModel.PERFORMANCE);

        // Now we switch the GPU Model to the Efficiency Model
        autonomousForklift.getAiEngine().switchGpuModel();

        // And we test it
        assertEquals(autonomousForklift.getAiEngine().getGpuModel(), GPUModel.EFFICIENCY);
    }

    /*
     Testing, if a ememergency stop really triggers emergencyStop() in AutonomousForklift.
    */
    @Test
    public void testEmergencyStop() {
        assertThrowsExactly(RuntimeException.class, autonomousForklift::emergencyStop);
    }

    /*
     Testing, if the AutonomousForklift can accelerate and decelerate.
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
     Here will
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

            // Holt sich die Palette aus dem Array
            Pallet pallet = palletsFromTrailer[storeIndex];
            // Testet das Label der Palette auf den richtigen Store
            assertEquals(store, pallet.getLabel().toString());
            // Schaut, ob jede Palette auch 27 Boxen hat
            assertEquals(27, pallet.getNumBoxes());

            // Jetzt werden von jeder Palette noch die 27 Boxen überprüft
            for (int b = 0; b < 27; b++) {
                Box box = pallet.getBox();

                // Schaut, ob in der Box auch wirklich 9 Flaschen drinne sind
                assertEquals(9, box.getBottlesCount());

                Bottle[] bottles = box.getBottleArray();
                for (int t = 0; t < box.getBottlesCount(); t++) {
                    // Schaut, ob die Flaschen auch mit dem richtigen Gin befüllt wurden
                    assertEquals(taste, bottles[t].getTaste());

                    // Schaut, ob auf dem Label auch der richtige Gin Typ draufsteht
                    assertEquals("Tanqueray | " + entry.getValue(), bottles[t].getLabelText());
                }
            }
            storeIndex++;
        }
    }
}
