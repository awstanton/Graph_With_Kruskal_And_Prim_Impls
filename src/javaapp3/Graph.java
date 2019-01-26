
package javaapp3;

// maybe for this and tree to: return the actual element instead of the data
public abstract class Graph<T> implements GraphInf<T> {
    public abstract boolean insertVertex(T element);
    public abstract T searchVertex(T element);
    public abstract boolean deleteVertex(T element);
    public abstract boolean insertEdge(T element);
    public abstract T searchEdge(T element);
    public abstract boolean deleteEdge(T element);
}
class AdjList<T> extends Graph<T> {
    
}
class AdjMatrix<T> extends Graph<T> {
    
}


