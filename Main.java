package Oblig4;

public class Main {

    public static void main(String[] args) {

        BST<Integer, String> tree = new BST<>();
        tree.add(new Entry<>(22,"twentytwo"));
        tree.add(new Entry<>(6,"six"));
        tree.add(new Entry<>(12,"tvelve"));
        tree.add(new Entry<>(8,"eight"));
        tree.add(new Entry<>(5,"five"));
        tree.add(new Entry<>(16,"sixteen"));
        tree.add(new Entry<>(3,"three"));

        tree.inorderTraverse();

        tree.remove(new Entry<>(8,"eight"));
        tree.inorderTraverse();

//        BST<Integer> iTree = new BST<>();
//        iTree.add(7);
//        iTree.add(12);
//        iTree.add(4);
//        iTree.add(3);
//        iTree.add(9);
//        iTree.add(17);
//        iTree.add(6);
//        iTree.add(23);
//
//        iTree.inorderTraverse();

    }



}
