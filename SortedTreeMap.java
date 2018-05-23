package Oblig4;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K, V> {
//    private static final boolean RED   = false;
//    private static final boolean BLACK = true;
    //    BinaryNode<Entry<K,V> > root;

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

        while(current != null) {
            prev = current;
            current = current.getRightChild();
        }
        return prev.getEntry();
    }

    @Override
    public V add(K key, V value) {
        Entry<K, V> newEntry = new Entry<>(key, value);
        Entry<K, V> previous = null;

        previous = mainTree.add(newEntry);

        if (previous == null) {
            return null;
        } else {
            return previous.value;
        }
    }

    @Override
    public V add(Entry<K, V> entry) {
        Entry<K, V> previous = null;

        previous = mainTree.add(entry);

        if (previous == null) {
            return null;
        } else {
            return previous.value;
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

        Entry<K, V> temp = mainTree.remove22((K) key);
        System.out.println("key: "+temp.key+" val: "+ temp.value);
        return temp.value;

//        BinaryNode<K, V> tempNode =  mainTree.removeOld2((K) key);

//        return tempNode.getEntry().value;

//        return mainTree.remove2((K) key).getEntry().value;
//        return mainTree.remove3((K) key).getEntry().value;

//        BinaryNode<K, V> removed = removeRecursive(mainTree.getRootNode(), (K) key);
//        return removed.getEntry().value;
    }

    private BinaryNode<K, V> removeRecursive(BinaryNode<K,V> current, K key) {
        if (current == null) {
            throw new NoSuchElementException();
        }

//        if (key.equals(current.getEntry().key)) {
        if (keyComparator.compare(key, current.getEntry().key) == 0 ) {
//            mainTree.remove(current.getEntry());

//           Entry<K, V> tempEntry = mainTree.remove(current.getEntry());
//           return new BinaryNode<>(tempEntry);

//            return mainTree.remove3(key);

//            return mainTree.remove2(key);


//            if (current.getLeftChild() == null && current.getRightChild() == null) {
//                return null;
//            }
//            if (current.getRightChild() == null) {
//                return current.getRightChild();
//            }
//            if (current.getLeftChild() == null) {
//                return current.getRightChild();
//            }
//            BinaryNode<K, V> tempNode = findSmallestValue(current.getRightChild());
//            current.setEntry(tempNode.getEntry());
//            current.setRightChild(removeRecursive(current.getRightChild(), key));
//            return current;
        }
//        if (key.compareTo(current.getEntry().key) < 0 )
        else if (keyComparator.compare(key, current.getEntry().key) < 0 ) {
            current.setLeftChild((removeRecursive(current.getLeftChild(), key)));
        } else {
            current.setRightChild((removeRecursive(current.getRightChild(), key)));
        }

        return current;
    }

    private BinaryNode<K, V> findSmallestValue (BinaryNode<K, V> node) {
        if (node.getLeftChild() == null ) {
            return node;
        } else {
            return findSmallestValue(node.getLeftChild());
        }
//        return node.getLeftChild() == null ? node : findSmallestValue(node.getLeftChild());
    }

    @Override
    public V getValue(Object key) throws NoSuchElementException {
        BinaryNode<K, V> current = mainTree.getRootNode();
        K tempKey = (K) key;
        V result = null;

//        if (!containsKey(tempKey) ) {
//            throw new NoSuchElementException();
//        }

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
//            else {
//                throw new NoSuchElementException();
//            }
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
//        if (key.compareTo(current.getEntry().key ) < 0 )
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
        boolean val = false;

        if (current == null) {
            return false;
        }
        if (value.equals(current.getEntry().value ) ) {
            return true;
        }

        if (containsValueRecursive(current.getLeftChild(), value))
            return true;

        if (containsValueRecursive(current.getRightChild(), value))
            return true;

        return false;
    }

//    private List<K> traverseInOrder(BinaryNode<K,V> node, List<K> keyList) {
//
//        if (node != null) {
//            traverseInOrder(node.getLeftChild(),keyList);
////            System.out.println(node.getEntry().key + " Value "+node.getEntry().value);
//            keyList.add(node.getEntry().key);
//            traverseInOrder(node.getRightChild(),keyList);
//        }
//        return keyList;
//    }

    @Override
    public Iterable<K> keys() {
        List<K> keys = new LinkedList<>();
        // fill list with keys in order (in-order-traversal)
//        keys = traverseInOrder(mainTree.getRootNode(), keys);
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
        BinaryNode<K,V> current = mainTree.getRootNode();
        BinaryNode<K,V> prev = mainTree.getRootNode();

        while(current != null) {
            prev = current;
            if (keyComparator.compare(key,current.getEntry().key) == 0) {
                return current.getEntry();
            }
//            else if (keyComparator.compare(key,current.getEntry().key) < 0) {
//                current = current.getLeftChild();
//            }
            else if (keyComparator.compare(key,current.getEntry().key) > 0) {
                current = current.getRightChild();
            }
        }
        return prev.getEntry();
    }

    @Override
    public Entry<K, V> lowerOrEqualEntry(K key) {
        Iterator<Entry<K, V>> entryItr = entries().iterator();
        Entry<K,V> current = null;
        Entry<K,V> prev = null;

        while (entryItr.hasNext()) {
            current = entryItr.next();
            if (key.compareTo(current.key) == 0 ){
                System.out.println("double check current: "+current.key);
                return current;
            }
//            if (keyComparator.compare(key, current.key) == 0) {
//                System.out.println("returns current"+ current.key);
//                return current;
//            }
        }

        Iterator<Entry<K, V>> entryItr2 = entries().iterator();
        while (entryItr2.hasNext()  ) {
//            System.out.println("has: "+temp.next().key );
//            queue.enqueue(temp.next());
            current = entryItr2.next();
            System.out.println("key: "+ key);
            System.out.println("check Current key2: "+ current.key);
            if ((key.compareTo(current.key) < 0 ) ){
                System.out.println("triple check current: "+current.key);
//                System.out.println("triple check prev: "+prev.key);
//                if (prev != null) {
//                    System.out.println("prev: "+ prev.key);
//                    return prev;
//                }
                return prev;
            }
            else if (!entryItr2.hasNext() ) {
                return current;
            }
            prev = current;

        }



        return null;
    }


//    @Override
//    public Entry<K, V> k,lowerOrEqualEntry(K key) {
////        BinaryNode<K,V> current = mainTree.getRootNode();
//        BinaryNode<K,V> current = mainTree.getRootNode();
//        BinaryNode<K,V> prev = null;
//
//        if (current == null) {
//            return null;
//        }
//
////        if (prev == null ) {
////            return null;
////        }
//
//        while (current != null) {
//            if (keyComparator.compare(key,current.getEntry().key) == 0) {
////                System.out.println("found key:"+ current.getEntry().key);
//                return current.getEntry();
//            }
//            else if (keyComparator.compare(key,current.getEntry().key) < 0) {
////                System.out.println("prev key: "+current.getEntry().key);
//                prev = current;
////                System.out.println("prev now key: "+prev.getEntry().key);
//                current = current.getLeftChild();
//
////                System.out.println("current key: "+current.getEntry().key);
//            } else if (keyComparator.compare(key,current.getEntry().key) > 0 ) {
//                prev = current;
//                current = current.getRightChild();
//            }
//
//        }
////        System.out.println("finally prev: "+ prev.getEntry().key);
////        System.out.println("finally current: "+ current.getEntry().key);
//        current = mainTree.getRootNode();
//        while (current != null) {
//             if (keyComparator.compare(key,current.getEntry().key) < 0) {
//                current = current.getLeftChild();
//
//                getLowerThan(current, key);
//             } else if (keyComparator.compare(key,current.getEntry().key) > 0 ) {
//                 current = current.getRightChild();
//             }
//
//        }
//        return null;
//
////        while(current != null ) {
////            if (size() == 1) {
////                if (keyComparator.compare(key,current.getEntry().key) == 0) {
////                    return current.getEntry();
////                }
////                else if (keyComparator.compare(key,current.getEntry().key) < 0) {
////                    current = current.getLeftChild();
////                }
////            } else {
////                prev = current;
////                if (keyComparator.compare(key,current.getEntry().key) == 0) {
////                    return current.getEntry();
////                }
////                else if (keyComparator.compare(key,current.getEntry().key) < 0) {
////                    current = current.getLeftChild();
////                }
////              else if (keyComparator.compare(key,current.getEntry().key) > 0) {
////                  current = current.getRightChild();
////              }
////            }
////        }
//
////        return prev.getEntry();
//    }

    private Entry<K,V> getLowerThan (BinaryNode<K,V> current, K key) {
        // if key are bigger than node right child return right child
        if (keyComparator.compare(key, current.getRightChild().getEntry().key) > 0) {
            return current.getRightChild().getEntry();
        }
        // if key bigger less than node right child and bigger than left child
        else if ( (keyComparator.compare(key, current.getRightChild().getEntry().key) < 0)  && ( keyComparator.compare(key, current.getLeftChild().getEntry().key) > 0)) {
            return current.getLeftChild().getEntry();
        } else {
            return null;
        }

    }

//    private BinaryNode<K,V> lowerOrEqualEntryRecursive (BinaryNode<K,V> root, K key) {
//        BinaryNode<K,V> current = root;
//        BinaryNode<K,V> prev = root;
//
//        while(current != null) {
//            if (key.compareTo(current.getEntry().key) == 0) {
//                return current;
//            } else if (key.compareTo(current.getEntry().key) < 0) {
//                prev = current;
//                current = current.getLeftChild();
//            } else if (key.compareTo(current.getEntry().key) > 0) {
//                prev = current;
//                current = current.getRightChild();
//            }
//        }
//        return prev;
//
////        if (current == null) {
////            return prev;
////        }
////        if (key.equals(current.getEntry().key ) ) {
////            return current;
////        }
////
////        if (key.compareTo(current.getEntry().key ) < 0 ) {
////            return lowerOrEqualEntryRecursive(current.getLeftChild(), key);
////        } else {
////            return lowerOrEqualEntryRecursive(current.getRightChild(), key);
////        }
//    }

//    public class Solution {
//        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//            if (t1 == null)
//                return t2;
//            if (t2 == null)
//                return t1;
//            t1.val += t2.val;
//            t1.left = mergeTrees(t1.left, t2.left);
//            t1.right = mergeTrees(t1.right, t2.right);
//            return t1;
//        }
//    }

//    public BinaryNode<K,V> mergeTrees (BinaryNode<K,V> root1,BinaryNode<K,V> root2) {
//        if (root1 == null) {
//            return root2;
//        }
//        if (root2 == null) {
//            return root1;
//        }
//        if (root1.) {
//
//        }
//
//    }

    @Override
    public void merge(ISortedTreeMap<K, V> other) {
//        ArrayList<BinaryNode<K,V>> tempList = new ArrayList<>();
//        tempList = storeInOrderTree((SortedTreeMap<K,V>)other, tempList);
//        BinaryNode<K,V> root = mainTree.getRootNode();
        Iterator<Entry<K,V>> itrEntries= other.entries().iterator();
        while (itrEntries.hasNext() ) {
            Entry<K, V> newNode = itrEntries.next();
            if (containsKey(newNode.key) ) {
                replace(newNode.key, newNode.value);
            }
            mainTree.add(newNode);
        }


    }

    private ArrayList<BinaryNode<K,V>> storeInOrderTree(BinaryNode<K,V> root, ArrayList<BinaryNode<K,V>> list) {
        if (root != null) {
            storeInOrderTree(root.getLeftChild(), list);
//            System.out.println(root.getEntry().key + " Value "+node.getEntry().value);
            list.add(root);
            storeInOrderTree(root.getRightChild(), list);

        }
        return list;
    }


    @Override
    public void removeIf(BiPredicate<K, V> p) {
        BinaryNode<K, V> current = mainTree.getRootNode();

        removeIfTravese(current,p);
    }

    private void removeIfTravese(BinaryNode<K,V> node, BiPredicate<K, V> p) {
        if (node != null) {
            removeIfTravese(node.getLeftChild(), p);
            if (p.test(node.getEntry().key, node.getEntry().value) ) {
                mainTree.remove22(node.getEntry().key);
            }
            removeIfTravese(node.getRightChild(), p);
        }

    }

//    public void filter(Predicate<? super E> filter) {
//        // take each element and use filter on each againt them
//        Node<E> tempNode = firstNode;
//        while (tempNode != null) {
//            boolean res = filter.test(tempNode.getData());
//            if (res) {
//                remove(tempNode.getData());
//                numberOfEntries--;
//            }
//            tempNode = tempNode.getNextNode();
//        }
//
//    }

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
