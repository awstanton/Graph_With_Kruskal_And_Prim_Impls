
package javaapp3;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AdjListTest {
    private GregorianCalendar a1;
    private GregorianCalendar b1;
    private GregorianCalendar c1;
    private GregorianCalendar d1;
    private GregorianCalendar e1;
    private GregorianCalendar a2;
    private GregorianCalendar b2;
    private GregorianCalendar c2;
    private GregorianCalendar d2;
    private GregorianCalendar e2;
    private AdjList instance;
    
    public AdjListTest() {
        a1 = new GregorianCalendar();
        b1 = new GregorianCalendar();
        c1 = new GregorianCalendar();
        d1 = new GregorianCalendar();
        e1 = new GregorianCalendar();
        a2 = new GregorianCalendar();
        b2 = new GregorianCalendar();
        c2 = new GregorianCalendar();
        d2 = new GregorianCalendar();
        e2 = new GregorianCalendar();
        instance = new AdjList(12);
    }

    /**
     * Test of createVertex method, of class AdjList.
     */
    @Test
    public void testCreateVertex() {
        assertEquals(a1, instance.createVertex(a1).data);
        assertEquals(null, instance.createVertex(b1).first);
        assertEquals(2, instance.createVertex(c1).index);
        assertEquals(3, instance.getVertexCount());
        assertEquals(0, instance.getEdgeCount());
    }

    /**
     * Test of createEdge method, of class AdjList.
     */
    
    @Test
    public void testCreateEdge() {
        System.out.println("createEdge");
        AdjList.Vertex v1 = instance.createVertex(a1);
        AdjList.Vertex v2 = instance.createVertex(b1);
        AdjList.Vertex v3 = instance.createVertex(c1);
        AdjList.Vertex v4 = instance.createVertex(d1);
        AdjList.Vertex v5 = instance.createVertex(e1);
        AdjList.Vertex v6 = instance.createVertex(a1);
        AdjList.Edge edge1 = instance.createEdge(v1, v2, a2);
        AdjList.Edge edge2 = instance.createEdge(v2, v1, b2);
        AdjList.Edge edge3 = instance.createEdge(v3, v3, c2);
        AdjList.Edge edge4 = instance.createEdge(v3, v4, d2);
        AdjList.Edge edge5 = instance.createEdge(v5, v6, b2);
        assertEquals(v1.first, edge1);
        assertEquals(v2.first, edge2);
        assertEquals(v3.first, edge3);
        assertEquals(v3.first.next, edge4);
        assertEquals(v4.first, null);
        assertEquals(v5.first, edge5);
        assertEquals(v6.first, null);
        assertEquals(edge1.toVertex, v2);
        assertEquals(edge2.toVertex, v1);
        assertEquals(edge3.toVertex, v3);
        assertEquals(edge4.toVertex, v4);
        assertEquals(edge5.toVertex, v6);
        assertEquals(edge1.fromVertex, v1);
        assertEquals(edge2.fromVertex, v2);
        assertEquals(edge3.fromVertex, v3);
        assertEquals(edge4.fromVertex, v3);
        assertEquals(edge5.fromVertex, v5);
        assertEquals(edge1.edgeData, a2);
        assertEquals(v1.data, v2.data);
        assertEquals(v1.data, a1);
        assertEquals(v2.data, a1);
    }
}
