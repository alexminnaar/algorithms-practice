package com.github.alexminnaar.graphs;

import java.util.*;

public class GraphExample {

    public static void main(String[] args) {

        //create vertex names
        List<String> vertexNames = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

        //create vertices and map them to their names
        Map<String, Vertex> vertexMap = new HashMap<>();
        for (String vn : vertexNames) vertexMap.put(vn, new Vertex(vn));

        //Add edges to vertices
        //A->B and B -> A
        vertexMap.get("A").addNeighbour(new Edge(vertexMap.get("A"), vertexMap.get("B"), 4));
        vertexMap.get("B").addNeighbour(new Edge(vertexMap.get("B"), vertexMap.get("A"), 4));

        //A->C and C->A
        vertexMap.get("A").addNeighbour(new Edge(vertexMap.get("A"), vertexMap.get("C"), 8));
        vertexMap.get("C").addNeighbour(new Edge(vertexMap.get("C"), vertexMap.get("A"), 8));

        //B->C and C->B
        vertexMap.get("B").addNeighbour(new Edge(vertexMap.get("B"), vertexMap.get("C"), 9));
        vertexMap.get("C").addNeighbour(new Edge(vertexMap.get("C"), vertexMap.get("B"), 9));

        //B->D and D->B
        vertexMap.get("B").addNeighbour(new Edge(vertexMap.get("B"), vertexMap.get("D"), 8));
        vertexMap.get("D").addNeighbour(new Edge(vertexMap.get("D"), vertexMap.get("B"), 8));

        //C->D and D->C
        vertexMap.get("C").addNeighbour(new Edge(vertexMap.get("C"), vertexMap.get("D"), 2));
        vertexMap.get("D").addNeighbour(new Edge(vertexMap.get("D"), vertexMap.get("C"), 2));

        //C->F and F->C
        vertexMap.get("C").addNeighbour(new Edge(vertexMap.get("C"), vertexMap.get("F"), 1));
        vertexMap.get("F").addNeighbour(new Edge(vertexMap.get("F"), vertexMap.get("C"), 1));

        //B->E and E->B
        vertexMap.get("B").addNeighbour(new Edge(vertexMap.get("B"), vertexMap.get("E"), 10));
        vertexMap.get("E").addNeighbour(new Edge(vertexMap.get("E"), vertexMap.get("B"), 10));

        //D->E and E->D
        vertexMap.get("D").addNeighbour(new Edge(vertexMap.get("D"), vertexMap.get("E"), 7));
        vertexMap.get("E").addNeighbour(new Edge(vertexMap.get("E"), vertexMap.get("D"), 7));

        //D->F and F->D
        vertexMap.get("D").addNeighbour(new Edge(vertexMap.get("D"), vertexMap.get("F"), 9));
        vertexMap.get("F").addNeighbour(new Edge(vertexMap.get("F"), vertexMap.get("D"), 9));

        //E->F and F->E
        vertexMap.get("E").addNeighbour(new Edge(vertexMap.get("E"), vertexMap.get("F"), 5));
        vertexMap.get("F").addNeighbour(new Edge(vertexMap.get("F"), vertexMap.get("E"), 5));

        //E->G and G->E
        vertexMap.get("E").addNeighbour(new Edge(vertexMap.get("E"), vertexMap.get("G"), 6));
        vertexMap.get("G").addNeighbour(new Edge(vertexMap.get("G"), vertexMap.get("E"), 6));

        //F->G and G->F
        vertexMap.get("F").addNeighbour(new Edge(vertexMap.get("F"), vertexMap.get("G"), 2));
        vertexMap.get("G").addNeighbour(new Edge(vertexMap.get("G"), vertexMap.get("F"), 2));


        //create graph from vertices
        Graph myGraph = new Graph(new ArrayList<>(vertexMap.values()));

        //perform breadth-first search and depth-first search
        //Set<Vertex> bfsReachable = myGraph.bfs();
        //Set<Vertex> dfsReachable = myGraph.dfs();

        //get minimum spanning tree via Prim's algorithm
        //myGraph.primMst();

        HashMap<Vertex, Integer> vertexDistances = myGraph.dijkstra(vertexMap.get("A"));

        for (Vertex v : vertexDistances.keySet()) {
            System.out.println(String.format("%s: %d", v.getValue(), vertexDistances.get(v)));
        }

    }
}
