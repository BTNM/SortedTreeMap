package Oblig4;

public class BinaryNode<K,V> {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Entry<K,V> entry;
    private BinaryNode<K,V> leftChild;
    private BinaryNode<K,V> rightChild;
    private boolean color; // color of parent link
    int balanceFactor = 0;

//    public BinaryNode() {
//        this(null);
//    }

    public BinaryNode(Entry<K,V> newData) {
        this(newData,null, null);
    }
    public BinaryNode(Entry<K,V>newData, BinaryNode<K,V> newLeftChild, BinaryNode<K,V> newRightChild) {
        entry = newData;
        leftChild = newLeftChild;
        rightChild = newRightChild;
    }
    public BinaryNode(Entry<K,V>newData, BinaryNode<K,V> newLeftChild, BinaryNode<K,V> newRightChild, boolean newColor) {
        entry = newData;
        leftChild = newLeftChild;
        rightChild = newRightChild;
        color = newColor;
    }



    public Entry<K, V> getEntry() {
        return entry;
    }

    public void setEntry(Entry<K, V> entry) {
        this.entry = entry;
    }

    public BinaryNode<K,V> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryNode<K,V> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryNode<K,V> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryNode<K,V> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }

    public boolean isLeaf() {
        return (leftChild == null) && (rightChild == null);
    }

//    /**
//     * Counts the nodes in the subtree rooted at this node
//     * @return The number of nodes in the subtree rooted at this node
//     */
//    public int getNumberOfNodes() {
//        int leftNumber = 0;
//        int rightNumber = 0;
//        if (leftChild != null) {
//            leftNumber = leftChild.getNumberOfNodes();
//        }
//        if (rightChild != null) {
//            rightNumber = rightChild.getNumberOfNodes();
//        }
//
//        return 1 + leftNumber + rightNumber;
//
//    }

    public int getHeight() {
        return getHeight(this);
    }

    /**
     * Computes the height of the subtree rooted at this node, with itself and the height of the subtrees tallest subtree
     * @param node
     * @return The height of the subtree rooted at this node
     */
    private int getHeight(BinaryNode<K,V> node) {
        int height = 0;
        if ( node != null) {
            height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
        }
        return height;
    }

    /**
     * Copies the subtree rooted at this node
     * @return The root of a copy of the subtree rooted
     */
    public BinaryNode<K,V> copy () {
        BinaryNode<K,V> newRoot = new BinaryNode<>(entry);

        if (leftChild != null) {
            newRoot.setLeftChild(leftChild.copy() );
        }
        if (rightChild != null) {
            newRoot.setRightChild(rightChild.copy());
        }
        return newRoot;

    }



}
