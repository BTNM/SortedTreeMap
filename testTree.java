package Oblig4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testTree  {
    BST<Integer,String> bst ;

    @BeforeEach
    public void setup() {
        bst = new BST<>();
        bst.add(new Entry(12,"hello12"));
        bst.add(new Entry(13,"hello13"));


}
    @Test
    public void testRemove()
    {
        bst.remove(13);
     //   assertEquals(1,bst.size());
        assertEquals(new Integer(12) ,bst.getRootNode().getEntry().key);
    }

    @Test
    public void checklowerOrEqual() {
        SortedTreeMap<Integer, String> treeMap = new SortedTreeMap<Integer, String>(new Entry<>(24,"twentyfour"));
        treeMap.add(23,"twentythree");
        treeMap.add(25,"twentythree");
        treeMap.add(21,"twentyOne");

        Iterator<Entry<Integer,String>> itr = treeMap.entries().iterator();
        while (itr.hasNext()) {
            System.out.println("in itr: "+itr.next().key);
        }

//        Entry<Integer, String> temp = treeMap.lowerOrEqualEntry(24);
//        System.out.println("key: "+temp.key+" value: "+ temp.value);
//        assertEquals(new Integer(24), temp.key);

        Entry<Integer, String> temp2 = treeMap.lowerOrEqualEntry(26);
        System.out.println("key21: "+temp2.key+" value21: "+ temp2.value);

        assertEquals(new Integer(25), temp2.key);

    }

    @Test
    public void checkHigherOrEqual() {
        SortedTreeMap<Integer, String> treeMap = new SortedTreeMap<Integer, String>(new Entry<>(24,"twentyfour"));
        treeMap.add(23,"twentythree");
        treeMap.add(25,"twentythree");
        treeMap.add(30,"twentyOne");
        treeMap.add(27,"twentyOne");

        Iterator<Entry<Integer,String>> itr = treeMap.entries().iterator();
        while (itr.hasNext()) {
            System.out.println("in itr: "+itr.next().key);
        }

        Entry<Integer, String> temp = treeMap.higherOrEqualEntry(24);
//        System.out.println("key: "+temp.key+" value: "+ temp.value);
        assertEquals(new Integer(24), temp.key);

        Entry<Integer, String> temp2 = treeMap.higherOrEqualEntry(26);
        System.out.println("key21: "+temp2.key+" value21: "+ temp2.value);
//
        assertEquals(new Integer(27), temp2.key);

    }

}
