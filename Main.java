package Oblig4;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

//        BST<Integer, String> tree = new BST<>();
//        tree.add(new Entry<>(22,"twentytwo"));
//        tree.add(new Entry<>(6,"six"));
//        tree.add(new Entry<>(12,"tvelve"));
//        tree.add(new Entry<>(8,"eight"));
//        tree.add(new Entry<>(5,"five"));
//        tree.add(new Entry<>(16,"sixteen"));
//        tree.add(new Entry<>(3,"three"));
//
//        tree.inorderTraverse();
//
//        tree.remove(new Entry<>(8,"eight"));
//        tree.inorderTraverse();

        SortedTreeMap<Integer, String> treeMap = new SortedTreeMap<Integer, String>(new Entry<>(22,"twentytwo"));
        treeMap.add(new Entry<>(6,"six"));
        treeMap.add(new Entry<>(12,"tvelve"));
        treeMap.add(new Entry<>(8,"eight"));
        treeMap.add(new Entry<>(5,"five"));
        treeMap.add(new Entry<>(16,"sixteen"));
//        treeMap.add(new Entry<>(3,"three"));

        Iterator<Integer> it = treeMap.keys().iterator();
        while(it.hasNext()) {
            System.out.println(it.next().intValue());
        }
//        Iterator<String> str = treeMap.values().iterator();
//        while(str.hasNext()) {
//            System.out.println(str.next());
//        }
        for (String s : treeMap.values()) {
            System.out.println(s);
        }

    }



}
