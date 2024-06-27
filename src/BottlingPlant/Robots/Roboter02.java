package BottlingPlant.Robots;

import BottlingPlant.StorageArea;
import Storage.Box;

public class Roboter02 extends Robot {
    private final StorageArea storageArea;
    private final Gripper gripper;

    public Roboter02(Gripper gripper) {
        super();
        this.storageArea = new StorageArea();
        this.gripper = gripper;
    }

    public Gripper getGripper() {
        return gripper;
    }

    public StorageArea getStorageArea() {
        return storageArea;
    }

    public void setFullBoxToStorage(Box box) {
        if (super.getState()) {
            // From old Project "Abfüllanlage"
            //storageArea.push(box);
            // For new Project "GinShop"
            gripper.addBoxToPallets(box);
        } else {
            throw new RuntimeException("Roboter02 is not active!");
        }
    }

    public Box getEmptyBoxFromTrack() {
        if (super.getState()) {
            return super.getTrack().getNextBox();
        } else {
            throw new RuntimeException("Roboter02 is not active!");
        }
    }
}
