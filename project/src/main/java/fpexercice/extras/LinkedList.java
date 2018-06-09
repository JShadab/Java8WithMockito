package fpexercice.extras;

import java.util.Optional;

public class LinkedList<T> {
    private final T value;
    private final LinkedList<T> next;

    public LinkedList(T value, LinkedList<T> next) {
        this.value = value;
        this.next = next;
    }

    public LinkedList(T value) {
        this(value , null);
    }

    public T getValue() {
        return value;
    }

    public LinkedList<T> getNext() {
        return next;
    }
}
