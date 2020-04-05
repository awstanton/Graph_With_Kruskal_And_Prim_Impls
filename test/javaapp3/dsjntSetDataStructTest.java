/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class dsjntSetDataStructTest {
    private Object a;
    private Object b;
    private Object c;
    private Object d;
    private Object e;
    private dsjntSetDataStruct instance;
    
    public dsjntSetDataStructTest() {
        a = new Object();
        b = new Object();
        c = new Object();
        d = new Object();
        e = new Object();
        instance = new dsjntSetDataStruct();
        instance.makeset(a);
        instance.makeset(b);
        instance.makeset(c);
        instance.makeset(d);
        instance.makeset(e);
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

    @Test
    public void testFindSet() {
        System.out.println("findSet");
        setUpClass();
        assertEquals(b, instance.findSet(b));
    }
    @Test
    public void testUnion1() {
        System.out.println("union1");
        setUpClass();
        instance.union(a, b);
        assertEquals(instance.findSet(a), instance.findSet(b));
        assertNotEquals(instance.findSet(a), instance.findSet(c));
    }
    @Test
    public void testUnion2() {
        System.out.println("union2");
        setUpClass();
        instance.union(a, b);
        instance.union(c, d);
        assertEquals(instance.findSet(a), instance.findSet(b));
        assertEquals(instance.findSet(c), instance.findSet(d));
        assertNotEquals(instance.findSet(a), instance.findSet(c));
    }
    @Test
    public void testUnion3() {
        System.out.println("union3");
        setUpClass();
        instance.union(a, b);
        instance.union(c, d);
        instance.union(a, d);
        assertEquals(instance.findSet(a), instance.findSet(b));
        assertEquals(instance.findSet(b), instance.findSet(c));
        assertEquals(instance.findSet(c), instance.findSet(d));
        assertEquals(instance.findSet(d), instance.findSet(a));
    }
    @Test
    public void testUnion4() {
        System.out.println("union4");
        setUpClass();
        instance.union(a, b);
        instance.union(b, c);
        instance.union(c, d);
        instance.union(e, a);
    }
}
