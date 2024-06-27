import BottlingPlant.Robots.FillingMachine;
import BottlingPlant.Robots.Gripper;
import BottlingPlant.Robots.Roboter01;
import BottlingPlant.Robots.Roboter02;

import Enumerations.Tastes;
import Forklift.AutonomousForklift;
import Management.Orders;
import Storage.Bottle;
import Storage.Pallet;
import Storage.PalletQueue;
import Storage.Trailer;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestApplication {
    private final Trailer trailer = new Trailer();
    private final PalletQueue palletQueue = new PalletQueue();
    private final AutonomousForklift autonomousForklift = new AutonomousForklift(trailer);

    @Test
    public void testBottlingPlant() {
        FillingMachine fillingMachine = new FillingMachine();
        for (int i=0; i<27;i++) {
            fillingMachine.getTrack().addBottle(new Bottle());
        }
        Roboter02 roboter02 = new Roboter02(new Gripper(autonomousForklift, palletQueue));
        Roboter01 roboter01 = new Roboter01(fillingMachine, roboter02);
        fillingMachine.setRoboter01(roboter01);

        // Activate all Robots
        fillingMachine.setState(true);
        roboter01.setState(true);
        roboter01.getRoboter02().setState(true);

        // Fill 22 Bottles from Queue with Gin
        for (int i = 0; i < 22; i++) {
            fillingMachine.fillBottle(Tastes.LONDON_DRY_GIN);
        }
        // We expect two full Boxes in the Storage Area, after the FillingMachine filled 22 Bottles
        // Since each Box contains 9 Bottles, so we have 18 Bottles in Storage, meaning two Boxes, while
        // 4 other Bottles are still in a Box outside the Storage
        assertEquals(2, roboter02.getStorageArea().size());
    }

    @Test
    public void testEmergencyStop() {
        assertThrowsExactly(RuntimeException.class, autonomousForklift::emergencyStop);
    }

    @Test
    public void testForkliftMotor() {
        int testSpeed = 20;
        for (int i=0; i<testSpeed; i++) {
            autonomousForklift.accelerate();
        }
        assertEquals(testSpeed, autonomousForklift.getSpeed());

        for (int i=testSpeed; i>0; i--) {
            autonomousForklift.slowDown();
        }
        assertEquals(0, autonomousForklift.getSpeed());
    }

    @Test
    public void testMain() {
        // Create Fillingmachine
        FillingMachine fm = new FillingMachine();

        // Load orders
        TreeMap<String, String> storeMap = Orders.getOrdersSorted();

        // Initialize BottleQueue and PalletQueue
        for (Map.Entry<String, String> entry : storeMap.entrySet()) {
            Tastes taste = Tastes.valueOf(entry.getValue().replaceAll(" ", "_").toUpperCase());
            for (int i=0; i<27*9;i++) {
                fm.getTrack().addBottle(new Bottle(taste));
            }
            palletQueue.enqueue(new Pallet(entry.getKey()));
        }

        // Create other three roboter
        Gripper gripper = new Gripper(autonomousForklift, palletQueue);
        Roboter02 roboter02 = new Roboter02(gripper);
        Roboter01 roboter01 = new Roboter01(fm, roboter02);
        fm.setRoboter01(roboter01);

        // Activate all Robots
        fm.setState(true);
        roboter01.setState(true);
        roboter01.getRoboter02().setState(true);
        roboter01.getRoboter02().getGripper().setState(true);

        // Fill Bottles
        for (Map.Entry<String, String> entry : storeMap.entrySet()) {
            Tastes taste = Tastes.valueOf(entry.getValue().replaceAll(" ", "_").toUpperCase());
            for (int i=0; i<27*9;i++) {
                fm.fillBottle(taste);
            }
        }
    }

    @Test
    public void testForki() {
        AutonomousForklift autonomousForklift1 = new AutonomousForklift(new Trailer());
        autonomousForklift1.start();

        assertEquals(true, autonomousForklift1.getState());

        autonomousForklift1.stop();

        assertEquals(false, autonomousForklift1.getState());
    }
}
