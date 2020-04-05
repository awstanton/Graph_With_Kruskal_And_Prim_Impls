
package javaapp3;

import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import org.junit.Assert;

public class AdjListMstPrimTest {
    
    public AdjListMstPrimTest() {
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

    enum Vertices {
        A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7), I(8);
        private final Integer vertexId;
        Vertices(Integer id) { 
            vertexId = id;
        }
        Integer getVertexId() {
            return vertexId;
        }
    }
    
    enum Edges {
        AB(20), AC(10), BA(20), BD(30), CA(10), CD(40), CE(100), DB(30), DC(40), EC(100), EF(20), EG(20), FE(20), FG(20), GE(20), GF(20), GH(200), HG(200);
        private final Integer edgeId;
        Edges(Integer id) {
            edgeId = id;
        }
        Integer getEdgeWeight() {
            return edgeId;
        }
    }

    public void checkEdge(AdjList<Integer>.Edge temp, Vertices v1, Vertices v2, Edges e) {
        assertEquals(temp.fromVertex.data, v1.getVertexId());
        assertEquals(temp.toVertex.data, v2.getVertexId());
        assertEquals(temp.edgeData, e.getEdgeWeight());
    }
    
    @Test
    public void testAdjListMstAlgo() {
        System.out.println("adjListMstAlgo");
        AdjList<Integer> graph = new AdjList<>();
        AdjList<Integer>.Vertex a = graph.createVertex(Vertices.A.getVertexId());
        AdjList<Integer>.Vertex b = graph.createVertex(Vertices.B.getVertexId());
        AdjList<Integer>.Vertex c = graph.createVertex(Vertices.C.getVertexId());
        AdjList<Integer>.Vertex d = graph.createVertex(Vertices.D.getVertexId());
        AdjList<Integer>.Vertex e = graph.createVertex(Vertices.E.getVertexId());
        AdjList<Integer>.Vertex f = graph.createVertex(Vertices.F.getVertexId());
        AdjList<Integer>.Vertex g = graph.createVertex(Vertices.G.getVertexId());
        AdjList<Integer>.Vertex h = graph.createVertex(Vertices.H.getVertexId());
        AdjList<Integer>.Vertex i = graph.createVertex(Vertices.I.getVertexId());
        graph.createEdge(a, b, Edges.AB.getEdgeWeight());
        graph.createEdge(a, c, Edges.AC.getEdgeWeight());
        graph.createEdge(b, a, Edges.BA.getEdgeWeight());
        graph.createEdge(b, d, Edges.BD.getEdgeWeight());
        graph.createEdge(c, a, Edges.CA.getEdgeWeight());
        graph.createEdge(c, d, Edges.CD.getEdgeWeight());
        graph.createEdge(c, e, Edges.CE.getEdgeWeight());
        graph.createEdge(d, b, Edges.DB.getEdgeWeight());
        graph.createEdge(d, c, Edges.DC.getEdgeWeight());
        graph.createEdge(e, c, Edges.EC.getEdgeWeight());
        graph.createEdge(e, f, Edges.EF.getEdgeWeight());
        graph.createEdge(e, g, Edges.EG.getEdgeWeight());
        graph.createEdge(f, e, Edges.FE.getEdgeWeight());
        graph.createEdge(f, g, Edges.FG.getEdgeWeight());
        graph.createEdge(g, e, Edges.GE.getEdgeWeight());
        graph.createEdge(g, f, Edges.GF.getEdgeWeight());
        graph.createEdge(g, h, Edges.GH.getEdgeWeight());
        graph.createEdge(h, g, Edges.HG.getEdgeWeight());
        
        AdjListMstPrim instance = new AdjListMstPrim();
        AdjList<Integer> mst = instance.adjListMstAlgo(graph);
        AdjList<Integer>.Edge temp;
        
        temp = mst.getVertexAt(Vertices.A.getVertexId()).first;
        checkEdge(temp, Vertices.A, Vertices.B, Edges.AB);

        temp = temp.next;
        checkEdge(temp, Vertices.A, Vertices.C, Edges.AC);

        assertNull(temp.next);
        
        temp = mst.getVertexAt(Vertices.B.getVertexId()).first;
        checkEdge(temp, Vertices.B, Vertices.A, Edges.BA);

        temp = temp.next;
        checkEdge(temp, Vertices.B, Vertices.D, Edges.BD);
        
        assertNull(temp.next);

        temp = mst.getVertexAt(Vertices.C.getVertexId()).first;
        checkEdge(temp, Vertices.C, Vertices.A, Edges.CA);

        temp = temp.next;
        checkEdge(temp, Vertices.C, Vertices.E, Edges.CE);
        
        assertNull(temp.next);

        temp = mst.getVertexAt(Vertices.D.getVertexId()).first;
        checkEdge(temp, Vertices.D, Vertices.B, Edges.DB);
        
        assertNull(temp.next);

        temp = mst.getVertexAt(Vertices.E.getVertexId()).first;
        checkEdge(temp, Vertices.E, Vertices.C, Edges.EC);

        temp = temp.next;
        System.out.println("temp.edgeData = " + temp.edgeData);
        System.out.println("temp.fromvertex.data = " + temp.fromVertex.data);
        System.out.println("temp.tovertex.data = " + temp.toVertex.data);
        Edges currEdge = (Objects.equals(temp.toVertex.data, Vertices.F.getVertexId()) ? Edges.EF : Edges.EG);
        System.out.println("currEdge = " + currEdge);
        if (currEdge == Edges.EF) {
            checkEdge(temp, Vertices.E, Vertices.F, Edges.EF);
            temp = temp.next;
            System.out.println("temp.edgeData = " + temp.edgeData);
            System.out.println("temp.fromvertex.data = " + temp.fromVertex.data);
            System.out.println("temp.tovertex.data = " + temp.toVertex.data);
            if (temp == null) { // no EG
                temp = mst.getVertexAt(Vertices.F.getVertexId()).first.next;
                checkEdge(temp, Vertices.F, Vertices.E, Edges.FE);
                temp = temp.next;
                checkEdge(temp, Vertices.F, Vertices.G, Edges.FG);
                assertNull(temp.next);
                temp = mst.getVertexAt(Vertices.G.getVertexId()).first;
                checkEdge(temp,Vertices.G, Vertices.F, Edges.GF);
                temp = temp.next;
                checkEdge(temp, Vertices.G, Vertices.H, Edges.GH);
                assertNull(temp.next);
            }
            else { // no FG
                checkEdge(temp, Vertices.E, Vertices.G, Edges.EG);
                assertNull(temp.next);
                temp = mst.getVertexAt(Vertices.F.getVertexId()).first;
                checkEdge(temp, Vertices.F, Vertices.E, Edges.FE);
                assertNull(temp.next);
                temp = mst.getVertexAt(Vertices.G.getVertexId()).first;
                checkEdge(temp, Vertices.G, Vertices.E, Edges.GE);
                temp = temp.next;
                checkEdge(temp, Vertices.G, Vertices.H, Edges.GH);
                assertNull(temp.next);
            }
        }
        else { // no EF
            checkEdge(temp, Vertices.E, Vertices.G, Edges.EG);
            temp = mst.getVertexAt(Vertices.F.getVertexId()).first;
            checkEdge(temp, Vertices.F, Vertices.G, Edges.FG);
            assertNull(temp.next);
            temp = mst.getVertexAt(Vertices.G.getVertexId()).first;
            checkEdge(temp, Vertices.G, Vertices.E, Edges.GE);
            temp = temp.next;
            checkEdge(temp, Vertices.G, Vertices.F, Edges.GF);
            temp = temp.next;
            checkEdge(temp, Vertices.G, Vertices.H, Edges.GH);
            assertNull(temp.next);
        }
        temp = mst.getVertexAt(Vertices.H.getVertexId()).first;
        checkEdge(temp, Vertices.H, Vertices.G, Edges.HG);
        assertNull(temp.next);
        temp = mst.getVertexAt(Vertices.I.getVertexId()).first;
        assertNull(temp);
    }
}
