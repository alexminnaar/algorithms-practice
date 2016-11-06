package com.github.alexminnaar.graphs;


public class Edge {

    Vertex source;
    Vertex destination;
    int weight;

    public Edge(Vertex startSource, Vertex startDestination, int startWeight) {
        source = startSource;
        destination = startDestination;
        weight = startWeight;
    }
}
