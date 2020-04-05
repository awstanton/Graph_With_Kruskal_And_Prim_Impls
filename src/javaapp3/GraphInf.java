
package javaapp3;

public interface GraphInf<T extends Comparable<T>> {
    AdjList.Vertex createVertex(T vertexData);
    AdjList.Edge createEdge(AdjList.Vertex fromVertex, AdjList.Vertex toVertex, T edgeData);
    // deleteEdge
    // deleteVertex
}

// ideas:
// add database functionality, having a B or B+ or other tree(s) swapping in behind-the-scenes
// vertex stores a central object and an auxiliary object
// edge stores a central object and an auxiliary object
// search a graph from a given start point, not just from start of adjacency list

