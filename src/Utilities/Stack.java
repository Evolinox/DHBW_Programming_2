package Utilities;

import java.util.Arrays;

public class Stack<E> {
    private int size = 0;
    private Object[] elements;

    public Stack(int capacity) {
        elements = new Object[capacity];
    }

    public void push(E e) {
        if (size == elements.length) {
            reserveMemory();
        }

        elements[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        E e = (E) elements[--size];
        elements[size] = null;
        return e;
    }

    private void reserveMemory() {
        int size = elements.length * 2;
        elements = Arrays.copyOf(elements, size);
    }

    public int getSize() {
        return size;
    }
}
