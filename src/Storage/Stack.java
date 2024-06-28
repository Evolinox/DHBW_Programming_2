package Storage;

import java.util.ArrayList;

public class Stack<E> extends ArrayList<E> {
    public E pop() {
        E e = getStack();
        remove(size() - 1);
        return e;
    }

    public void push(E e) {
        add(e);
    }

    public E getStack() {
        E e = get(size() - 1);
        return e;
    }
}
