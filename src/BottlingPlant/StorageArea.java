package BottlingPlant;

import Storage.Box;

import java.util.ArrayList;

public class StorageArea extends ArrayList<Box> {
    public Box pop() {
        Box box = (Box) get(size() - 1);
        remove(size() - 1);
        return box;
    }

    // add/push Box
    public void push(Box box) {
        add(box);
    }
}
