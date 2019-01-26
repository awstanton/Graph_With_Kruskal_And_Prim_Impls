
package javaapp3;

/**
 *
 * @author Andrew
 */
public interface GraphInf<T> {
    boolean insertVertex(T element);
    T searchVertex(T element);
    boolean deleteVertex(T element);
    boolean insertEdge(T element);
    T searchEdge(T element);
    boolean deleteEdge(T element);
}
