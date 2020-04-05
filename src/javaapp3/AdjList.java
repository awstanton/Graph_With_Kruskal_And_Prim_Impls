
package javaapp3;

import java.util.ArrayList;

public class AdjList<T extends Comparable<T>> implements GraphInf<T> {
    private ArrayList<Vertex> l;
    private int vertexCount;
    private int edgeCount;

    public AdjList() {
        l = new ArrayList<>();
        vertexCount = 0;
        edgeCount = 0;
    }
    public AdjList(int size) {
        l = new ArrayList<>(size);
        vertexCount = 0;
        edgeCount = 0;
    }

    ArrayList<Vertex> getL() {
        return l;
    }
    
    Vertex getVertexAt(int index) {
        return l.get(index);
    }
    
    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }
    
    @Override
    public Vertex createVertex(T t) {
        l.add(new Vertex(t, l.size()));
        ++vertexCount;
        return l.get(l.size() - 1);
    }
    @Override
    public Edge createEdge(AdjList.Vertex fromVertex, AdjList.Vertex toVertex, T edgeData) {
        if (fromVertex.first == null) {
            fromVertex.first = new Edge(fromVertex, toVertex, edgeData);
            ++edgeCount;
            return fromVertex.first;
        }
        else {
            Edge temp = fromVertex.first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Edge(fromVertex, toVertex, edgeData);
            ++edgeCount;
            return temp.next;
        }
        
    }
   
    public class Vertex implements Comparable<Vertex> {
        T data;
        int index;
        Edge first;
        
        Vertex(T t, int index) {
            data = t;
            this.index = index;
            first = null; 
        }
        Vertex(Vertex v) {
            this.data = v.data;
            this.index = v.index;
            this.first = null;
        }

        @Override
        public int compareTo(Vertex o) {
            return data.compareTo(o.data);
        }
    }
    
    public class Edge implements Comparable<Edge> {
        Vertex fromVertex;
        Vertex toVertex;
        T edgeData;
        Edge next;
        
        Edge(Vertex fromVertex, Vertex toVertex) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            edgeData = null;
            next = null;
        }
        Edge(Vertex fromVertex, Vertex toVertex, T edgeData) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.edgeData = edgeData;
            next = null;
        }
        Edge(Vertex fromVertex, Vertex toVertex, Edge e) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.edgeData = e.edgeData;
            this.next = null;
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.edgeData.compareTo(e.edgeData);
        }
    }
}
