package Utilities;

import java.util.ArrayList;

public class Stack<E> extends ArrayList<E> {
    public E pop() {
        E e = (E) get(size() - 1);
        remove(size() - 1);
        return e;
    }

    // add/push Box
    public void push(E e) {
        add(e);
    }
}
