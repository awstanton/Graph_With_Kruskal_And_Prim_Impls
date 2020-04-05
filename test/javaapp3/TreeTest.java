
package javaapp3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class TreeTest {
    
    public TreeTest() {
    }
    
    /**
     * Test of insert method, of class Tree.
     */
    @Test
    public void testInsertNull() {
        System.out.println("insert null");
        Comparable element = null;
        Tree instance = new Tree();
        boolean result = instance.insert(element);
        assertEquals(false, result);
    }
    @Test
    public void testInsert() {
        System.out.println("insert");
        Comparable element = 89;
        Tree instance = new Tree(87);
        boolean result = instance.insert(element);
        assertEquals(true, result);
    }
    /**
     * Test of search method, of class Tree.
     */
    @Test
    public void testSearchNull() {
        System.out.println("searchNull");
        Comparable element = 56;
        Tree instance = new Tree(90);
        Object result = instance.search(element);
        assertEquals(false, result);
    }

        @Test
    public void testSearchTrue() {
        System.out.println("searchElement");
        Integer element1 = 47;
        Integer element2 = 56;
        Tree instance = new Tree(90);
        instance.insert(element1);
        instance.insert(element2);
        Boolean b = instance.search(56);
        assertEquals(true, instance.search(56));
        assertEquals(true, instance.search(90));
        assertEquals(true, instance.search(47));
    }
    
    @Test
    public void testDelete1() {
        System.out.println("delete1");
        Integer element1 = 47;
        Integer element2 = 56;
        Tree instance = new Tree(90);
        instance.insert(element1);
        instance.insert(element2);
        instance.delete(47);
        Object result = instance.search(47);
        assertEquals(false, result);
    }
    
    @Test
    public void testDelete2() {
        System.out.println("delete2");
        Integer element1 = 47;
        Integer element2 = 56;
        Integer element3 = 42;
        Tree instance = new Tree(90);
        instance.insert(element1);
        instance.insert(element2);
        instance.insert(element3);
        instance.delete(47);
        Object result = instance.search(47);
        assertEquals(false, result);
        assertEquals(true, instance.search(56));
        assertEquals(true, instance.search(42));
        assertEquals(true, instance.search(90));
    }
    @Test
    public void testDelete3() {
        System.out.println("delete3");
        Integer element1 = 47;
        Integer element2 = 56;
        Integer element3 = 42;
        Tree instance = new Tree(90);
        instance.insert(element1);
        instance.insert(element2);
        instance.insert(element3);
        instance.delete(47);
        instance.insert(element1);
        assertEquals(true, instance.search(47));
        instance.delete(47);
        assertEquals(false, instance.search(47));
    }
}
