package Oblig4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K, V> {
//    private static final boolean RED   = false;
//    private static final boolean BLACK = true;
    //    BinaryNode<Entry<K,V> > root;

    BST<K,V> mainTree = new BST<>();
    Comparator<K> keyComparator;

    public SortedTreeMap ( Entry<K,V> newEntry) {

    }

    public SortedTreeMap(Comparator<K> keyComparator) {
        this.keyComparator = keyComparator;
    }

    @Override
    public Entry min() {
        BinaryNode<K,V> current = mainTree.root;
        BinaryNode<K,V> prev = mainTree.root;
        while(current != null) {
            prev = current;
            current = current.getLeftChild();
        }
        return prev.getEntry();
    }

    @Override
    public Entry max() {
        BinaryNode<K,V> current = mainTree.root;
        BinaryNode<K,V> prev = mainTree.root;
        while(current != null) {
            prev = current;
            current = current.getRightChild();
        }
        return prev.getEntry();
    }

    @Override
    public V add(K key, V value) {
        return null;
    }

    @Override
    public V add(Entry<K, V> entry) {
        return null;
    }

    @Override
    public void replace(K key, V value) throws NoSuchElementException {

    }

    @Override
    public void replace(K key, BiFunction<K, V, V> f) throws NoSuchElementException {

    }

    @Override
    public V remove(Object key) throws NoSuchElementException {
        return null;
    }

    @Override
    public V getValue(Object key) throws NoSuchElementException {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }

    @Override
    public Iterable<V> values() {
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        return null;
    }

    @Override
    public Entry<K, V> higherOrEqualEntry(K key) {
        return null;
    }

    @Override
    public Entry<K, V> lowerOrEqualEntry(K key) {
        return null;
    }

    @Override
    public void merge(ISortedTreeMap<K, V> other) {

    }

    @Override
    public void removeIf(BiPredicate<K, V> p) {

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
