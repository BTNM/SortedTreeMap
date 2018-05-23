//package Oblig4;
//
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//import java.util.function.Consumer;
//
//public class InorderIterator<T> implements Iterator<T> {
////    private Stack<BinaryNode<T>> nodeStack;
////    private BinaryNode<K,V> currentNode;
////
////    public InorderIterator (BinaryNode<T> root) {
////        nodeStack = new LinkedStack<>();
////        currentNode = root;
////    }
////
////    @Override
////    public boolean hasNext() {
////        return !nodeStack,isEmpty() || (currentNode != null);
////    }
////
////    @Override
////    public T next() {
////        BinaryNode<T> nextNode = null;
////
////        while (currentNode != null) {
////            nodeStack.push(currentNode);
////            currentNode = currentNode.getLeftChild();
////        }
////
////        if (!nodeStack.isEmpty()) {
////            nextNode = nodeStack.pop();
////            assert nextNode != null;
////            currentNode = nextNode.getRightChild();
////        } else {
////            throw new NoSuchElementException();
////        }
////        return nextNode.getEntry();
////    }
////
////    @Override
////    public void remove() {
////        throw new UnsupportedOperationException();
////    }
////
////    @Override
////    public void forEachRemaining(Consumer<? super T> action) {
////
////    }
//}
//
//
