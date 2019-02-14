/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp3;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Andrew
 */

// THE ERROR WAS I WAS USING AdjList.Edge, not AdjList<T>.Edge!!!!!!!!!!!
public class AdjListMstKruskal<T extends Comparable<T>> extends AdjListMstAlgo<T> {

    public Tree<AdjList<T>.Edge> adjListMstAlgo(AdjList<T> a) {
        Tree<AdjList<T>.Edge> mstEdgeSet = new Tree<AdjList<T>.Edge>();
        dsjntSetDataStruct<AdjList<T>.Vertex> disjointSet = new dsjntSetDataStruct<>();
        
        ArrayList<AdjList<T>.Edge> tempEdgeSet = new ArrayList<>();
        
        for (AdjList<T>.Vertex v : a.getL()) {
            disjointSet.makeset(v);
            AdjList<T>.Edge temp = v.first;
            while (temp != null) {
                tempEdgeSet.add(temp);
            }
        }
        //ArrayList<dsjntSetElmnt<AdjList.Vertex>> dseList = new ArrayList<>();
        //MAYBE RECREATE EDGE SET TO REFER TO REPRESENTATIVES SET IN DISJOINT SET DATA STRUCTURE CLASS
        // BUT IN THAT CASE ENSURE THE COMPLEXITY PROPERTIES ARE SATISFIED
        
        // ASSUMING THE EDGES' DATA IS THEIR WEIGHT
        Collections.sort(tempEdgeSet);
        for (AdjList<T>.Edge e : tempEdgeSet) {
            if (disjointSet.findSet(e.fromVertex) != disjointSet.findSet(e.toVertex)) {
                mstEdgeSet.insert(e);
                disjointSet.union(e.fromVertex, e.toVertex);
            }
        }
        return mstEdgeSet;
    }
}
