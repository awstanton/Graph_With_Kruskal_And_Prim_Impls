
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compare method, of class Tree.
     */
    @Test
    public void testCompare1() {
        System.out.println("compare1");
        Comparable t1 = 100;
        Comparable t2 = 100;
        Tree instance = new Tree();
        int expResult = 0;
        int result = instance.compare(t1, t2);
        assertEquals(expResult, result);
    }
    @Test
    public void testCompare2() {
        System.out.println("compare2");
        Tree instance = new Tree();
        int result = instance.compare("a string", "a string");
        assertEquals(0, result);
    }
    @Test
    public void testCompare3() {
        System.out.println("compare3");
        Tree instance = new Tree();
        int compareResult = instance.compare("a string", "a string that is longer");
        boolean result = (compareResult < 0);
        assertEquals(true, result);
    }

    /**
     * Test of insert method, of class Tree.
     */
    @Test
    public void testInsertNull() {
        System.out.println("insert null");
        Object element = null;
        Tree instance = new Tree();
        boolean result = instance.insert(element);
        assertEquals(false, result);
    }
    @Test
    public void testInsert() {
        System.out.println("insert");
        Object element = 89;
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
        Object element = 56;
        Tree instance = new Tree(90);
        Object result = instance.search(element);
        assertEquals(null, result);
    }

        @Test
    public void testSearchTrue() {
        System.out.println("searchElement");
        Integer element1 = 47;
        Integer element2 = 56;
        Tree instance = new Tree(90);
        instance.insert(element1);
        instance.insert(element2);
        Integer result = (Integer) instance.search(56);
        assertEquals(element2, result);
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
        assertEquals(null, result);
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
        assertEquals(null, result);
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
        Object result = instance.search(42);
        assertEquals(42, result);
    }    
}
