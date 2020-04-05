
package javaapp3;

import java.util.ArrayList;

public class AdjListMstPrim<T extends Comparable<T>> implements AdjListMstAlgo<T> {
    private ArrayList<primElement> primElements;
    
    
    @Override
    public AdjList<T> adjListMstAlgo(AdjList<T> a) {
        
        if (a == null || a.getVertexCount() < 1)
            return null;
        
        primElements = new ArrayList<>(a.getVertexCount());
        AdjList<T> mst = new AdjList<>(a.getVertexCount());
        for (int index = 0; index < a.getVertexCount(); ++index)
            primElements.add(new primElement(index));
        
        primElements.get(0).keyValue = a.getVertexAt(0).data;
        
        minPriorityQueuePrim<T> mpq = new minPriorityQueuePrim<>(primElements);
        
        // compute parent of each primElement
        while (!mpq.isEmpty()) {
            primElement p = mpq.heapExtractMin();
            System.out.println(p);
            p.inQueue = false;
            int currIndex = p.index;
            
            AdjList<T>.Vertex v1 = a.getVertexAt(currIndex);
            AdjList<T>.Edge e = v1.first;

//            System.out.println("MIN KEYVALUE = " + p.keyValue);
//            System.out.println("CURRINDEX = " + currIndex);
//            System.out.println("PRIM ELEMENTS = " + primElements);
            
            while (e != null) {
                AdjList<T>.Vertex v2 = e.toVertex;
                if (primElements.get(v2.index).inQueue) {
//                    System.out.println("primElements.get(v2.index).inQueue = " + primElements.get(v2.index).inQueue);
//                    System.out.println("e.edgeData = " + e.edgeData);
//                    System.out.println("primElements.get(v2.index).keyValue = " + primElements.get(v2.index).keyValue);
                    if (primElements.get(v2.index).compareToEdgeData(e.edgeData) > 0) {
                        primElements.get(v2.index).parentIndex = v1.index;
                        primElements.get(v2.index).keyValue = e.edgeData;
//                        System.out.println("queueIndex = " + primElements.get(v2.index).queueIndex);
                        mpq.heapDecreaseKey(primElements.get(v2.index).queueIndex, primElements.get(v2.index));
                    }
                }
                e = e.next;
            }
        }
        
        // use parent of each primElement to construct minimum spanning tree
        for (int index = 0; index < a.getVertexCount(); ++index)
            mst.createVertex(a.getVertexAt(index).data);
        
        for (int index = 0; index < a.getVertexCount(); ++index) {
            primElement crntElmnt = primElements.get(index);
            int parentIndex = crntElmnt.parentIndex;
            if (parentIndex >= 0 && parentIndex < a.getVertexCount()) {
                AdjList<T>.Edge e1 = a.getVertexAt(index).first;
                AdjList<T>.Edge e2 = a.getVertexAt(parentIndex).first;

                while (e1 != null && e1.toVertex.index != parentIndex)
                    e1 = e1.next;
                while (e2 != null && e2.toVertex.index != index)
                    e2 = e2.next;

                // since UNDIRECTED graph, either both edges present or neither edge present
                if (e1 != null && e2 != null) {
                    mst.createEdge(mst.getVertexAt(index), mst.getVertexAt(parentIndex), e1.edgeData);
                    mst.createEdge(mst.getVertexAt(parentIndex), mst.getVertexAt(index), e2.edgeData);
                }
            }
            
        }
        return mst;
    }
    
    public class primElement implements Comparable<primElement> {
        private T keyValue;
        private int parentIndex;
        private boolean inQueue;
        private int index;
        private int queueIndex;

        private primElement(int index) {
            keyValue = null;
            parentIndex = -1;
            inQueue = true;
            this.index = index;
            queueIndex = index;
        }

        @Override
        public int compareTo(primElement o) {
            if (keyValue == null)
                return 1;
            else if (o.keyValue == null)
                return -1;
            else
                 return (this.keyValue).compareTo(o.keyValue);
        }
        public int compareToEdgeData(T edgeData) {
            if (keyValue == null)
                return 1;
            else if (edgeData == null)
                return -1;
            else
                return (keyValue.compareTo(edgeData));
        }
        @Override
        public String toString() {
            if (keyValue == null)
                return "null";
            else
                return "keyValue = " + keyValue.toString();
        }
        public void updateQueueIndex(int newIndex) {
            queueIndex = newIndex;
        }
    }
}