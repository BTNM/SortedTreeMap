package Oblig4;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K, V> {
    BST<K,V> mainTree = new BST<>();
    Comparator<K> keyComparator;

    public SortedTreeMap ( Entry<K,V> newEntry) {
        mainTree.add(newEntry);
    }

    public SortedTreeMap(Comparator<K> keyComparator) {
        this.keyComparator = keyComparator;
    }

    @Override
    public Entry<K, V> min() {
        BinaryNode<K,V> current = mainTree.getRootNode();
        BinaryNode<K,V> prev = mainTree.getRootNode();
        if (current == null || prev == null) {
            return null;
        }
        // when hits the leftmost element in tree hits a null element, gives the previous min element
        while(current != null) {
            prev = current;
            current = current.getLeftChild();
        }
        return prev.getEntry();
    }

    @Override
    public Entry<K, V> max() {
        BinaryNode<K,V> current = mainTree.getRootNode();
        BinaryNode<K,V> prev = mainTree.getRootNode();
        if (current == null || prev == null) {
            return null;
        }
        // when hits the rightmost element in tree hits a null element, gives the previous max element
        while(current != null) {
            prev = current;
            current = current.getRightChild();
        }
        return prev.getEntry();
    }

    @Override
    public V add(K key, V value) {
        Entry<K, V> newEntry = new Entry<>(key, value);

        Entry<K, V> previousElement = mainTree.add(newEntry);

        if (previousElement == null) {
            return null;
        } else {
            return previousElement.value;
        }
    }

    @Override
    public V add(Entry<K, V> entry) {
        Entry<K, V> previousElement = mainTree.add(entry);

        if (previousElement == null) {
            return null;
        } else {
            return previousElement.value;
        }
    }

    @Override
    public void replace(K key, V value) throws NoSuchElementException {
        BinaryNode<K, V> current = mainTree.getRootNode();

        if (!containsKey(key)) {
            throw new NoSuchElementException();
        }

        while (current != null) {
            if (keyComparator.compare(key,current.getEntry().key) == 0) {
                current.setEntry(new Entry<>(key, value));
                return;
            }
            else if (keyComparator.compare(key,current.getEntry().key) < 0) {
                current = current.getLeftChild();
            }
            else if (keyComparator.compare(key,current.getEntry().key) > 0) {
                current = current.getRightChild();
            }
        }

    }

    @Override
    public void replace(K key, BiFunction<K, V, V> f) throws NoSuchElementException {
        BinaryNode<K, V> current = mainTree.getRootNode();

        if (!containsKey(key)) {
            throw new NoSuchElementException();
        }

        while (current != null) {
            if (keyComparator.compare(key,current.getEntry().key) == 0) {
                // apply function at entry with key, and replace entry's value with output from function
                V tempV = f.apply(key,current.getEntry().value);
                current.setEntry(new Entry<>(key, tempV));
                return;
            }
            else if (keyComparator.compare(key,current.getEntry().key) < 0) {
                current = current.getLeftChild();
            }
            else if (keyComparator.compare(key,current.getEntry().key) > 0) {
                current = current.getRightChild();
            }
        }
    }


    @Override
    public V remove(Object key) throws NoSuchElementException {
        if (!containsKey((K) key)) {
            throw new NoSuchElementException();
        }
        Entry<K, V> temp = mainTree.remove((K) key);
        return temp.value;
    }

    @Override
    public V getValue(Object key) throws NoSuchElementException {
        BinaryNode<K, V> current = mainTree.getRootNode();
        K tempKey = (K) key;
        if (!containsKey(tempKey) ) {
            throw new NoSuchElementException();
        }

        while (current != null) {
            if (keyComparator.compare(tempKey,current.getEntry().key) == 0) {
                return current.getEntry().value;
            }
            else if (keyComparator.compare(tempKey,current.getEntry().key) < 0) {
                current = current.getLeftChild();
            }
            else if (keyComparator.compare(tempKey,current.getEntry().key) > 0) {
                current = current.getRightChild();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyRecursive(mainTree.getRootNode(), key);
    }
    private boolean containsKeyRecursive (BinaryNode<K, V> current, K key) {
        if (current == null) {
            return false;
        }
        if (key.equals(current.getEntry().key ) ) {
            return true;
        }
        // check left child if key less than current's key
        if (keyComparator.compare(key, current.getEntry().key) < 0)
        {
            return containsKeyRecursive(current.getLeftChild(), key);
        } else {
            return containsKeyRecursive(current.getRightChild(), key);
        }
    }


    @Override
    public boolean containsValue(V value) {
        return containsValueRecursive(mainTree.getRootNode(), value);
    }
    private boolean containsValueRecursive (BinaryNode<K, V> current, V value) {
        if (current == null) {
            return false;
        }
        if (value.equals(current.getEntry().value ) ) {
            return true;
        }

        if (containsValueRecursive(current.getLeftChild(), value)){
            return true;
        }

        if (containsValueRecursive(current.getRightChild(), value)) {
            return true;
        }

        return false;
    }

    @Override
    public Iterable<K> keys() {
        List<K> keys = new LinkedList<>();
        // fill list with keys in order (in-order-traversal)
        keys = mainTree.traverseInOrderKeys(mainTree.getRootNode(), keys);
        return keys;
    }

    @Override
    public Iterable<V> values() {
        List<V> values = new LinkedList<>();

        values = mainTree.traverseInOrderValues(mainTree.getRootNode(), values);
        return values;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        List<Entry<K,V>> entries = new LinkedList<>();
        entries = mainTree.traverseInOrderEntries(mainTree.getRootNode(), entries);
        return entries;
    }

    @Override
    public Entry<K, V> higherOrEqualEntry(K key) {
        Iterator<Entry<K, V>> entryItr = entries().iterator();
        Iterator<Entry<K, V>> entryItr2 = entries().iterator();
        Entry<K,V> current = null;

        // check if has equal entry in tree
        while (entryItr.hasNext()) {
            current = entryItr.next();
            if (key.compareTo(current.key) == 0 ){
                return current;
            }
        }
        // look for next higher entry if exists
        while (entryItr2.hasNext()  ) {
            current = entryItr2.next();
            if ((key.compareTo(current.key) < 0 ) ){
                return current;
            }
        }

        return null;
    }

    @Override
    public Entry<K, V> lowerOrEqualEntry(K key) {
        Iterator<Entry<K, V>> entryItr = entries().iterator();
        Entry<K,V> current = null;
        Entry<K,V> prev = null;

        // check if has equal entry in tree
        while (entryItr.hasNext()) {
            current = entryItr.next();
            if (key.compareTo(current.key) == 0 ){
                return current;
            }
        }
        // look for next lower entry if exists
        Iterator<Entry<K, V>> entryItr2 = entries().iterator();
        while (entryItr2.hasNext()  ) {
            current = entryItr2.next();

            if ((key.compareTo(current.key) < 0 ) ){
                return prev;
            }
            else if (!entryItr2.hasNext() ) {
                return current;
            }
            prev = current;
        }

        return null;
    }

    @Override
    public void merge(ISortedTreeMap<K, V> other) {
        Iterator<Entry<K,V>> itrEntries = other.entries().iterator();

        while (itrEntries.hasNext() ) {
            Entry<K, V> newNode = itrEntries.next();
            if (containsKey(newNode.key) ) {
                replace(newNode.key, newNode.value);
            }
            mainTree.add(newNode);
        }

    }

    @Override
    public void removeIf(BiPredicate<K, V> p) {
        BinaryNode<K, V> current = mainTree.getRootNode();

        removeIfTraverse(current,p);
    }

    private void removeIfTraverse(BinaryNode<K,V> node, BiPredicate<K, V> p) {
        if (node != null) {
            // traverse through all elements in tree and test predicate on them
            removeIfTraverse(node.getLeftChild(), p);

            if (p.test(node.getEntry().key, node.getEntry().value) ) {
                mainTree.remove(node.getEntry().key);
            }
            removeIfTraverse(node.getRightChild(), p);
        }

    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return mainTree.size();
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            BinaryNode<K, V> rootNode = mainTree.getRootNode();
            rootNode.setEntry(null);
            rootNode.setRightChild(null);
            rootNode.setLeftChild(null);
        }
    }

}
