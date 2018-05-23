package Oblig4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.function.Consumer;

public class QueueItr<T> implements Iterable<T> {
    private LinkedList<T> elements = new LinkedList<>();

    public void enqueue (T element) {
        elements.add(element);
    }
    public T dequeue () {
        return elements.removeFirst();
    }

    public void clear() {
        elements.clear();
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }


    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
