package Oblig4;

import java.util.List;
import java.util.NoSuchElementException;

public class BST<K extends Comparable<? super K>,V> {
    private BinaryNode<K,V> root;
    private BinaryNode<K, V> tempBinaryNode;

    public BinaryNode<K, V> getTempBinaryNode() {
        return tempBinaryNode;
    }

    public void setTempBinaryNode(BinaryNode<K, V> tempBinaryNode) {
        this.tempBinaryNode = tempBinaryNode;
    }
    //    public BST (T rootData) {
//        root = new BinaryNode<>(rootData);
//    }

    public BST () {

    }

    public BST (Entry<K,V> newEntry) {
        root.setEntry(newEntry);
    }

    private BinaryNode<K, V> rotateRight (BinaryNode<K,V> nodeN) {
        BinaryNode<K, V> nodeC = nodeN.getLeftChild();
        nodeN.setLeftChild(nodeC.getRightChild());
        nodeC.setRightChild(nodeN);
        return nodeC;
    }
    private BinaryNode<K, V> rotateRightLeft (BinaryNode<K, V> nodeN) {
        BinaryNode<K, V> nodeC = nodeN.getRightChild();
        nodeN.setRightChild(rotateRight(nodeC));
        return rotateLeft(nodeN);
    }

    private BinaryNode<K,V> rotateLeft(BinaryNode<K, V> nodeN) {
        BinaryNode<K, V> nodeC = nodeN.getRightChild();
        nodeN.setRightChild(nodeC.getLeftChild());
        nodeC.setLeftChild(nodeN);
        return nodeC;
    }
    private BinaryNode<K, V> rotateLeftRight (BinaryNode<K, V> nodeN) {
        BinaryNode<K, V> nodeC = nodeN.getLeftChild();
        nodeN.setLeftChild(rotateLeft(nodeC));
        return rotateRight(nodeN);
    }

    /**
     * addition occured in
     *  left subtree of N's left child - right rotation
     *  right subtree of N's left child - leftRight rotation
     *  right subtree of N's right child - left rotation
     *  left subtree of N's right child - rightLeft rotation
     */
    private BinaryNode<K, V> rebalance (BinaryNode<K, V> nodeN) {
        int heightDifference = getHeightDifference(nodeN);

        if (heightDifference > 1) {
            // left subtree is taller, addition occured in left subtree
            if (getHeightDifference(nodeN.getLeftChild()) > 0) {
                // addtition in left subtree of left child
                nodeN = rotateRight(nodeN);
            } else {
                // addtition in right subtree of left child
                nodeN = rotateLeftRight(nodeN);
            }
            // right subtree is taller, addition in right
        } else if (heightDifference < -1) {
            if (getHeightDifference(nodeN.getRightChild()) < 0) {
                // addition in right subtree of right child
                nodeN = rotateLeft(nodeN);
            } else {
                // addition in left subtree of right child
                nodeN = rotateRightLeft(nodeN);
            }
        }
        return nodeN;
    }

    public int getHeightDifference (BinaryNode<K,V> node) {
        int leftHeight = 0;
        int rightHeight = 0;

        if (node.getLeftChild() != null) {
            leftHeight = node.getLeftChild().getHeight();
        } else {
            leftHeight = 0;
        }
        if (node.getRightChild() != null) {
            rightHeight = node.getRightChild().getHeight();
        } else {
            rightHeight = 0;
        }

        return leftHeight - rightHeight;

//        return node.getLeftChild().getHeight() - node.getRightChild().getHeight();
    }

//    public boolean contain (K key) {
//
//
//    }


    public Entry<K, V> add (Entry<K, V> newEntry) {
        Entry<K, V> result = null;
        if (isEmpty() ) {
            setRootNode(new BinaryNode<>(newEntry) );
        } else {
            BinaryNode<K, V> rootNode = getRootNode();
            result = addEntry(rootNode, newEntry);
            setRootNode(rebalance(rootNode));
        }
        return result;
    }

    private Entry<K,V> addEntry(BinaryNode<K, V> rootNode, Entry<K, V> newEntry) {
        assert rootNode != null;
        Entry<K, V> result = null;
        int compared = newEntry.key.compareTo(rootNode.getEntry().key);

        if (compared == 0) {
            result = rootNode.getEntry();
            rootNode.setEntry(newEntry);
        } else if (compared < 0) {
            if (rootNode.hasLeftChild()) {
                BinaryNode<K, V> leftChild = rootNode.getLeftChild();
                result = addEntry(leftChild, newEntry);
                rootNode.setLeftChild(rebalance(leftChild));
            } else {
                rootNode.setLeftChild(new BinaryNode<>(newEntry));
            }
        } else {
            assert compared > 0;
            if (rootNode.hasRightChild()) {
                BinaryNode<K, V> rightChild = rootNode.getRightChild();
                result = addEntry(rightChild, newEntry);
                rootNode.setRightChild(rebalance(rightChild));
            } else {
                rootNode.setRightChild(new BinaryNode<>(newEntry));
            }
        }
        return result;
    }




    public BinaryNode<K, V> remove2 (K key) {
        return remove2(root, key);
    }

    private BinaryNode<K, V> remove2 (BinaryNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if ( key.compareTo(node.getEntry().key) < 0 ) {
            node.setLeftChild(remove2(node.getLeftChild(), key));
            return rebalance(node);
        } else if (key.compareTo(node.getEntry().key) > 0 ){
            node.setRightChild(remove2(node.getRightChild(), key));
            return rebalance(node);
        } else {
            // 0 children
//            if (!node.hasLeftChild() && !node.hasRightChild() ) {
            if ( node.getLeftChild() == null && node.getRightChild() == null ) {
                return null;
            }
//            if (!node.hasLeftChild() ) {
            if (node.getLeftChild() == null ) {
                return node.getRightChild();
            }
//            if (!node.hasRightChild()) {
            if (node.getRightChild() == null ) {
                return node.getLeftChild();
            }

            Entry<K,V> smallest = smallest(node.getRightChild());
            node.setEntry(smallest);
            node.setRightChild(remove2(node.getRightChild(),smallest.key ));
            return rebalance(node);

        }

    }

    private Entry<K,V> smallest (BinaryNode<K, V> node) {
        if (node.getLeftChild() == null) {
            return node.getEntry();
        }
        return smallest(node.getLeftChild());
    }


    public BinaryNode<K, V> remove3 (K key) {
        BinaryNode<K, V> rootNode = getRootNode();
        BinaryNode<K, V> removedNode = removeNode(rootNode, key);
//        setRootNode(removedNode);

        return removedNode;
    }

    private BinaryNode<K, V> removeNode(BinaryNode<K, V> rootNode, K key) {
        if (root == null) {
            throw new NoSuchElementException();
        }

        int compared = key.compareTo(rootNode.getEntry().key);

        if (compared < 0) {

            rootNode.setLeftChild(removeNode(rootNode.getLeftChild(),key));
        } else if (compared > 0 ) {
            BinaryNode<K, V> removed = removeNode(rootNode.getRightChild(), key);
            rootNode.setRightChild(removed);

        } else if (rootNode.getLeftChild() != null && rootNode.getRightChild() != null) {
            rootNode.setEntry(smallest(rootNode.getRightChild() ));
            rootNode.setRightChild(removeNode(rootNode, key));
        } else {
            if (rootNode.getLeftChild() != null) {
                rootNode = rootNode.getLeftChild();
            } else {
                rootNode = rootNode.getRightChild();
            }
        }
        return rebalance(rootNode);

    }


//    public Entry<K,V> remove(Entry<K,V> entry) {
//        ReturnObject oldEntry = new ReturnObject(null);
//        BinaryNode<K, V> newRoot = removeEntry(getRootNode(), entry, oldEntry);
//        setRootNode(newRoot);
////        setRootNode(rebalance(newRoot));
//
//        return oldEntry.getOldEntry();
//    }
//
//    private BinaryNode<K, V> removeEntry(BinaryNode<K, V> rootNode, Entry<K,V> entry, ReturnObject oldEntry) {
//        if (rootNode != null) {
//            Entry<K,V> rootData = rootNode.getEntry();
//            int compared = entry.key.compareTo(rootData.key);
//
//            if (compared == 0) {
//                oldEntry.setOldEntry(rootData);
//                rootNode = removeFromRoot(rootNode);
//            } else if (compared < 0) {
//                BinaryNode<K, V> leftChild = rootNode.getLeftChild();
//                BinaryNode<K, V> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
//                rootNode.setLeftChild(subtreeRoot);
////                return rebalance(rootNode);
////                rootNode.setLeftChild(rebalance(subtreeRoot));
//            } else {
//                BinaryNode<K, V> rightChild = rootNode.getRightChild();
//                BinaryNode<K, V> subtreeRoot = removeEntry(rightChild, entry, oldEntry);
//                rootNode.setRightChild(subtreeRoot);
////                return rebalance(rootNode);
////                rootNode.setRightChild(rebalance(subtreeRoot));
//            }
//        }
//        return rootNode;
//    }

    public BinaryNode<K, V> removeOld2(K key) {
        BinaryNode<K, V> newRoot = removeEntry2(getRootNode(), key);
//        setRootNode(newRoot);
        return newRoot;

//        if (tempBinaryNode != null ) {
//            return tempBinaryNode;
//        } else {
//            return null;
//        }

//        return tempBinaryNode != null ? tempBinaryNode : null;
    }

    private BinaryNode<K, V> removeEntry2(BinaryNode<K, V> rootNode, K key) {
        if (rootNode != null) {
            Entry<K,V> rootData = rootNode.getEntry();
            Entry<K,V> removed;
            int compared = key.compareTo(rootData.key);

            if (compared == 0) {
//                tempBinaryNode.setEntry(rootData);
//                NodePair<BinaryNode<K,V>> temp;
                removed = rootNode.getEntry();
                System.out.println(removed.key);
                rootNode = removeFromRoot(rootNode);
                System.out.println(rootNode.getEntry().key);
//                if (rootNode != null) {
//                    rebalance(rootNode);
//                }
                return new BinaryNode<>(removed);
            } else if (compared < 0) {
                BinaryNode<K, V> leftChild = rootNode.getLeftChild();
                BinaryNode<K, V> subtreeRoot = removeEntry2(leftChild, key);
                rootNode.setLeftChild(subtreeRoot);
            } else {
                BinaryNode<K, V> rightChild = rootNode.getRightChild();
                BinaryNode<K, V> subtreeRoot = removeEntry2(rightChild, key);
                rootNode.setRightChild(subtreeRoot);
            }
        }
        return rootNode;
    }

    private BinaryNode<K, V> removeFromRoot(BinaryNode<K, V> rootNode) {
        // rootNode has two children
        if (rootNode.hasLeftChild() && rootNode.hasRightChild() ) {
            // find largest entry in left subtree
            BinaryNode<K, V> leftSubtreeOfRoot = rootNode.getLeftChild();
            BinaryNode<K, V> largestNode = findLargest(leftSubtreeOfRoot);

            // replace entry in root
            rootNode.setEntry(largestNode.getEntry() );
            // remove node with largest entry in left subtree
            rootNode.setLeftChild(removeLargest(leftSubtreeOfRoot) );
            // removing may give an unbalanced tree
//            return rebalance(rootNode);
        }
        // rootNode has at most 1 child
        else if (rootNode.hasRightChild() ) {
            rootNode = rootNode.getRightChild();
        } else {
            rootNode = rootNode.getLeftChild();
//            rootNode.setEntry(null);
        } // if rootNode was a leaf, then it is now full

        return rootNode;
    }

    private BinaryNode<K, V> removeLargest(BinaryNode<K, V> rootNode) {
        if (rootNode.hasRightChild() ) {
            BinaryNode<K, V> rightChild = rootNode.getRightChild();
            // removes the largest rightchild of leftsubtree

            rightChild = removeLargest(rightChild);
            rootNode.setRightChild(rightChild);
        } else {
//            rootNode.setEntry(null);
//            rootNode = null;
            rootNode = rootNode.getLeftChild();
        }
        return rootNode;
    }

    private BinaryNode<K, V> findLargest(BinaryNode<K, V> rootNode) {
        if (rootNode.hasRightChild() ) {
            rootNode = findLargest(rootNode.getRightChild() );
        }
        return rootNode;
    }

    private class ReturnObject {
        Entry<K,V> oldEntry;

        public ReturnObject ( Entry<K,V> oldEntry) {
            this.oldEntry = oldEntry;
        }

        public  Entry<K,V> getOldEntry() {
            return oldEntry;
        }

        public void setOldEntry( Entry<K,V> oldEntry) {
            this.oldEntry = oldEntry;
        }
    }

    private class NodePair<T> {
        T first;
        T second;

        public NodePair (T first, T second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public T getSecond() {
            return second;
        }

        public void setSecond(T second) {
            this.second = second;
        }
    }



//    public int getHeight(BinaryNode node) {
//        if (node == null) {
//            return 0;
//        } else {
//            return Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()) )+1;
//        }
//    }

//    public int getHeight() {
//        return root.getHeight();
//    }
//    public int getNumberOfNodes() {
//        return root.getNumberOfNodes();
//    }
    public int size() {
        return size(root);
    }
    private int size(BinaryNode<K,V> node) {
        if (node == null || node.getEntry() == null) {
            return (0);
        } else {
            return (size(node.getLeftChild()) + size(node.getRightChild()) + 1 );
        }

    }


    public Entry<K,V> getRootEntry() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        } else {
            return root.getEntry();
        }
    }
    protected boolean isEmpty() {
        return root == null;
    }
    public void clear() {
        root = null;
    }
    protected void setRootData (Entry<K,V> rootData) {
        root.setEntry(rootData);
    }
    protected void setRootNode(BinaryNode<K,V> rootNode) {
        root = rootNode;
    }
    protected BinaryNode<K,V> getRootNode () {
        return root;
    }

    public void inOrderTraverse() {
        inorderTraverse(root);
    }
    private void inorderTraverse(BinaryNode<K,V> node) {
        if (node != null && node.getEntry() != null) {
            inorderTraverse(node.getLeftChild());
            System.out.println(node.getEntry().key + " Value "+node.getEntry().value);
            inorderTraverse(node.getRightChild());
        }

    }
    public List<K> traverseInOrderKeys(BinaryNode<K, V> node, List<K> keyList) {
        if (node != null && node.getEntry() != null) {
            traverseInOrderKeys(node.getLeftChild(),keyList);
//            System.out.println(node.getEntry().key + " Value "+node.getEntry().value);
            keyList.add(node.getEntry().key);
            traverseInOrderKeys(node.getRightChild(),keyList);
        }
        return keyList;
    }

    public List<V> traverseInOrderValues(BinaryNode<K, V> node, List<V> list) {

        if (node != null && node.getEntry() != null) {
            traverseInOrderValues(node.getLeftChild(),list);
//            System.out.println(node.getEntry().key + " Value "+node.getEntry().value);
            list.add(node.getEntry().value);
            traverseInOrderValues(node.getRightChild(),list);
        }
        return list;
    }

    public List<Entry<K,V>> traverseInOrderEntries(BinaryNode<K, V> node, List<Entry<K,V>> list) {

        if (node != null && node.getEntry() != null) {
            traverseInOrderEntries(node.getLeftChild(), list );
//            System.out.println(node.getEntry().key + " Value "+node.getEntry().value);
            list.add(node.getEntry());
            traverseInOrderEntries(node.getRightChild(), list);
        }
        return list;
    }



//    public Entry<K,V> getEntry (Entry<K,V> entry) {
//        return findEntry(getRootNode(), entry);
//    }
//
//    private Entry<K,V> findEntry(BinaryNode<K,V> rootNode, Entry<K,V> entry) {
//        Entry<K,V> result = null;
//        if (rootNode != null) {
//            Entry<K,V> rootEntry = rootNode.getEntry();
//            if (entry.equals(rootEntry)) {
//                result = rootEntry;
//            } else if (entry.compareTo(rootEntry) < 0) {
//                result = findEntry(rootNode.getLeftChild(), entry);
//            } else {
//                result = findEntry(rootNode.getRightChild(), entry);
//            }
//        }
//        return result;
//    }
//


}
