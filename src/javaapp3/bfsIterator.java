
package javaapp3;

// getNext() gets the next edge if there is one, if it does not find one after reaching the end, then it returns null
public class bfsIterator<T extends Comparable<T>> {
    private final AdjList<T> graph;
    private AdjList<T>.Edge currentEdge;
    private int currVrtxIndx;
    
    public bfsIterator(AdjList<T> g) {
        graph = g;
        currVrtxIndx = 0;
        if (!graph.getL().isEmpty())
            currentEdge = graph.getL().get(currVrtxIndx).first;
        else
            currentEdge = null;
    }
    
    public void reset() {
        currVrtxIndx = 0;
        currentEdge = getFirst();
    }
    
    // maybe throw exception in exceptional case - just need way to tell other class about what happened
    public AdjList<T>.Edge getNext() {
        if (currentEdge == null || currentEdge.next == null) {
            while (++currVrtxIndx < graph.getL().size()) {
                currentEdge = graph.getL().get(currVrtxIndx).first;
                if (currentEdge != null)
                    return currentEdge;
            }
            return null;
        }
        else {
            return currentEdge.next;
        }
    }
    
    public AdjList<T>.Edge getCurrent() {
        return currentEdge;
    }
    public AdjList<T>.Vertex getCurrFromVertex() {
        if (currentEdge == null)
            return null;
        return currentEdge.fromVertex;
    }
    public AdjList<T>.Vertex getCurrToVertex() {
        if (currentEdge == null)
            return null;
        return currentEdge.toVertex;
    }
    public int getCurrFromVrtxIndex() {
        return currentEdge.fromVertex.index;
    }
    public int getCurrToVrtxIndex() {
        return currentEdge.toVertex.index;
    }
    // returns the first non-null edge, if there is one
    public AdjList<T>.Edge getFirst() {
        if (!graph.getL().isEmpty()) {
            if (graph.getL().get(0).first != null) {
                return graph.getL().get(0).first;
            }
            else {
                return getNext();
            }
        }
        else {
            return null;
        }
    }
}
