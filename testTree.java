package Oblig4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        bst.removeOld2(13);
     //   assertEquals(1,bst.size());
        assertEquals(new Integer(12) ,bst.getRootNode().getEntry().key);
    }
}
