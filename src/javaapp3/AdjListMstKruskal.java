
package javaapp3;

import java.util.ArrayList;
import java.util.Collections;

// THE ERROR WAS I WAS USING AdjList.Edge, not AdjList<T>.Edge!!!!!!!!!!!
public class AdjListMstKruskal<T extends Comparable<T>> implements AdjListMstAlgo<T> {

    @Override
    public AdjList<T> adjListMstAlgo(AdjList<T> a) {
    
        dsjntSetDataStruct<AdjList<T>.Vertex> disjointSet = new dsjntSetDataStruct<>();
        ArrayList<AdjList<T>.Edge> tempEdges = new ArrayList<>(a.getEdgeCount()); // pass in count to ensure no automatic reallocation
        
        for (AdjList<T>.Vertex v : a.getL()) {
            disjointSet.makeset(v);
            AdjList<T>.Edge temp = v.first;
            while (temp != null) {
                tempEdges.add(temp);
                temp = temp.next;
            }
        }
        
        // initialize vertices
        AdjList<T> mst = new AdjList<>(a.getVertexCount());
        for (int index = 0; index < a.getL().size(); ++index)
            mst.createVertex(a.getL().get(index).data);
        
        // sort edges
        Collections.sort(tempEdges);
        for (AdjList<T>.Edge e : tempEdges) {
            if (disjointSet.findSet(e.fromVertex) != disjointSet.findSet(e.toVertex)) {
                // add TWO edges TOTAL (for undirected pair of vertices) to mst since it is UNDIRECTED
                // findSet will be same after first directed edge is created, so reverse will not otherwise be created
                mst.createEdge(mst.getL().get(e.fromVertex.index), mst.getL().get(e.toVertex.index), e.edgeData);
                mst.createEdge(mst.getL().get(e.toVertex.index), mst.getL().get(e.fromVertex.index), e.edgeData);
                // merge disjoint sets
                disjointSet.union(e.fromVertex, e.toVertex);
            }
        }
        return mst;
    }
}
