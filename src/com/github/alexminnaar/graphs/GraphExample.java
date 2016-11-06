package com.github.alexminnaar.graphs;

import java.util.*;

public class GraphExample {

    public static void main(String[] args) {

        //create vertex names
        List<String> vertexNames = Arrays.asList("A", "B", "C", "D","E","F","G");

        //create vertices and map them to their names
        Map<String, Vertex> vertexMap = new HashMap<>();
        for (String vn : vertexNames) vertexMap.put(vn, new Vertex(vn));

        //Add edges to vertices
        //A->B
        vertexMap.get("A").addNeighbour(new Edge(vertexMap.get("A"),vertexMap.get("B"),4));
        //A->C
        vertexMap.get("A").addNeighbour(new Edge(vertexMap.get("A"),vertexMap.get("C"),8));
        //B->C
        vertexMap.get("B").addNeighbour(new Edge(vertexMap.get("B"),vertexMap.get("C"),9));
        //B->D
        vertexMap.get("B").addNeighbour(new Edge(vertexMap.get("B"),vertexMap.get("D"),8));
        //C->D
        vertexMap.get("C").addNeighbour(new Edge(vertexMap.get("C"),vertexMap.get("D"),2));
        //B->E
        vertexMap.get("B").addNeighbour(new Edge(vertexMap.get("B"),vertexMap.get("E"),10));
        //D->E
        vertexMap.get("D").addNeighbour(new Edge(vertexMap.get("D"),vertexMap.get("E"),7));
        //D->F
        vertexMap.get("D").addNeighbour(new Edge(vertexMap.get("D"),vertexMap.get("F"),9));
        //E->F
        vertexMap.get("E").addNeighbour(new Edge(vertexMap.get("E"),vertexMap.get("F"),5));
        //E->G
        vertexMap.get("E").addNeighbour(new Edge(vertexMap.get("E"),vertexMap.get("G"),6));
        //F->G
        vertexMap.get("F").addNeighbour(new Edge(vertexMap.get("F"),vertexMap.get("G"),2));

        //create graph from vertices
        Graph myGraph = new Graph(new ArrayList<>(vertexMap.values()));

        //Set<Vertex> bfsReachable = myGraph.bfs();
        //Set<Vertex> dfsReachable = myGraph.dfs();

        myGraph.primMst();

    }
}
