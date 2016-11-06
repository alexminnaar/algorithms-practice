package com.github.alexminnaar.graphs;

import java.util.*;
import java.util.logging.Logger;


public class Graph {
    private static final Logger LOGGER = Logger.getLogger(Graph.class.getName());

    private ArrayList<Vertex> vertices;

    public Graph(ArrayList<Vertex> startVertices) {
        vertices = startVertices;
    }

    public void addVertex(final Vertex newVertex) {
        vertices.add(newVertex);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Breadth First Search algorithm: First provided vertex treated as the root
     *
     * @return A list of all vertices reachable from the root vertex.
     */
    public Set<Vertex> bfs() {

        //queue to hold not-yet-explored vertices
        Queue<Vertex> q = new LinkedList<>();
        //add root to queue
        q.add(vertices.get(0));

        //keep track of reachable vertices
        Set<Vertex> reachable = new HashSet<>();

        while (!q.isEmpty()) {
            Vertex v = q.remove();
            LOGGER.info(String.format("BFS - Exploring vertex %s ", v.getValue()));

            for (Edge neighbour : v.getNeighbours()) {
                //add neighbour to queue if it has not yet been seen
                if (!reachable.contains(neighbour.destination)) {
                    q.add(neighbour.destination);
                    reachable.add(neighbour.destination);
                }
            }
        }

        return reachable;
    }

    /**
     * Depth First Search algorithm: First provided vertex treated as the root
     *
     * @return A list of all vertices reachable from the root vertex.
     */
    public Set<Vertex> dfs() {

        Stack<Vertex> s = new Stack<>();
        s.push(vertices.get(0));

        //keep track of reachable vertices
        Set<Vertex> reachable = new HashSet<>();

        while (!s.isEmpty()) {
            Vertex v = s.pop();
            LOGGER.info(String.format("DFS - Exploring vertex %s ", v.getValue()));

            for (Edge neighbour : v.getNeighbours()) {
                if (!reachable.contains(neighbour.destination)) {
                    s.add(neighbour.destination);
                    reachable.add(neighbour.destination);
                }
            }
        }

        return reachable;
    }

    private Edge primMinEdge(Set<Vertex> s, Set<Vertex> vNotS) {

        LOGGER.info("Finding edge with minimum weight");

        int minWeight = Integer.MAX_VALUE;
        Edge minEdge = new Edge(new Vertex(""), new Vertex(""), 0);

        //find edge connecting vertex in spanning tree to vertex not in spanning tree with minimum weight
        for (Vertex v : s) {

            for (Edge vEdge : v.getNeighbours()) {
                if (vNotS.contains(vEdge.destination))
                    LOGGER.info(String.format("Edge %s -> %s has weight %d", vEdge.source.getValue(), vEdge.destination.getValue(), vEdge.weight));

                if (vNotS.contains(vEdge.destination) && vEdge.weight < minWeight) {
                    minWeight = vEdge.weight;
                    minEdge = vEdge;
                }
            }
        }

        LOGGER.info(String.format("Minimum Edge is  %s -> %s with weight %d", minEdge.source.getValue(), minEdge.destination.getValue(), minEdge.weight));

        return minEdge;
    }


    public ArrayList<Edge> primMst() {

        Set<Vertex> s = new HashSet<>();
        Set<Vertex> vNotS = new HashSet<>(vertices);
        ArrayList<Edge> mst = new ArrayList<>();

        //start with arbitrary root vertex
        s.add(vertices.get(0));
        vNotS.remove(vertices.get(0));

        while (!vNotS.isEmpty()) {

            //find lowest weighted edge to add to mst
            Edge minEdge = primMinEdge(s, vNotS);
            LOGGER.info(String.format("Adding %s -> %s with weight %d", minEdge.source.getValue(), minEdge.destination.getValue(), minEdge.weight));
            
            mst.add(minEdge);

            //add corresponding vertex to S and remove from V/S
            s.add(minEdge.destination);
            vNotS.remove(minEdge.destination);
        }

        return mst;
    }

}
