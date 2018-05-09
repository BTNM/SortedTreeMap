package Oblig4;

import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SortedTreeMap<K, V> implements ISortedTreeMap {

    //kap 25, 27


    @Override
    public Entry min() {
        return null;
    }

    @Override
    public Entry max() {
        return null;
    }

    @Override
    public Object add(Comparable key, Object value) {
        return null;
    }

    @Override
    public Object add(Entry entry) {
        return null;
    }

    @Override
    public void replace(Comparable key, Object value) throws NoSuchElementException {

    }

    @Override
    public void replace(Comparable key, BiFunction f) throws NoSuchElementException {

    }

    @Override
    public Object remove(Object key) throws NoSuchElementException {
        return null;
    }

    @Override
    public Object getValue(Object key) throws NoSuchElementException {
        return null;
    }

    @Override
    public boolean containsKey(Comparable key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Iterable keys() {
        return null;
    }

    @Override
    public Iterable values() {
        return null;
    }

    @Override
    public Iterable<Entry> entries() {
        return null;
    }

    @Override
    public Entry higherOrEqualEntry(Comparable key) {
        return null;
    }

    @Override
    public Entry lowerOrEqualEntry(Comparable key) {
        return null;
    }

    @Override
    public void merge(ISortedTreeMap other) {

    }

    @Override
    public void removeIf(BiPredicate p) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }


}
