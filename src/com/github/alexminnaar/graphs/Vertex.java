package com.github.alexminnaar.graphs;

import java.util.ArrayList;

public class Vertex {

    private String value;
    private ArrayList<Edge> neighbours;

    public Vertex(String startValue) {
        value = startValue;
        neighbours = new ArrayList<>();
    }

    public ArrayList<Edge> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Edge newNeighbour) {
        //add new edge to this vertex's edge list
        //TODO: Check that the source is actually this vertex
        neighbours.add(newNeighbour);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String newValue) {
        value = newValue;
    }

}
