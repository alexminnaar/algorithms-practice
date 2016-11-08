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

    /**
     * Prim's algorithm sub-routine to find minimum edge to add to minimum spanning tree.
     *
     * @param s     set of vertices currently in mst.
     * @param vNotS set of vertices currently not in mst.
     * @return minimum edge connecting vertex in S to vertex in V/S.
     */
    private Edge primMinEdge(Set<Vertex> s, Set<Vertex> vNotS) {

        LOGGER.info("Finding edge with minimum weight");

        int minWeight = Integer.MAX_VALUE;
        Edge minEdge = new Edge(new Vertex(""), new Vertex(""), 0);

        //find edge connecting vertex in spanning tree to vertex not in spanning tree with minimum weight
        for (Vertex v : s) {

            for (Edge vEdge : v.getNeighbours()) {
                if (vNotS.contains(vEdge.destination))
                    //LOGGER.info(String.format("Edge %s -> %s has weight %d", vEdge.source.getValue(), vEdge.destination.getValue(), vEdge.weight));

                    if (vNotS.contains(vEdge.destination) && vEdge.weight < minWeight) {
                        minWeight = vEdge.weight;
                        minEdge = vEdge;
                    }
            }
        }

        LOGGER.info(String.format("Minimum Edge is  %s -> %s with weight %d", minEdge.source.getValue(), minEdge.destination.getValue(), minEdge.weight));

        return minEdge;
    }

    /**
     * Prim's algorithm for finding the minimum spanning tree in a graph.
     *
     * @return A list of edges that define the minimum spanning tree.
     */
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


    /**
     * Get the closest vertex sub-routine for Dijkstra's algorithm.  This should actually be replaced with a priority queue.
     *
     * @param vertexDistances Distances for unvisited vertices.
     * @return vertex with minimum distance.
     */
    private Vertex getMinVertex(HashMap<Vertex, Integer> vertexDistances) {

        Vertex minVertex = new Vertex("");
        Integer minDistance = Integer.MAX_VALUE;

        for (Vertex v : vertexDistances.keySet()) {

            Integer vDist = vertexDistances.get(v);

            if (vDist < minDistance) {
                minVertex = v;
                minDistance = vertexDistances.get(v);
            }
        }

        return minVertex;
    }

    /**
     * Dijskta's shortest path algorithm
     *
     * @param source starting vertex in graph
     * @return Distances of shortest path from source to every other vertex in graph.
     */
    public HashMap<Vertex, Integer> dijkstra(Vertex source) {

        HashMap<Vertex, Integer> unvisitedVertices = new HashMap<>();
        HashMap<Vertex, Integer> vertexDistances = new HashMap<>();

        //set source distance to zero and the others to INFINITY
        vertices.forEach(v -> unvisitedVertices.put(v, Integer.MAX_VALUE));
        //TODO: check source vertex is actually in the graph
        unvisitedVertices.put(source, 0);

        Vertex current = source;

        while (!unvisitedVertices.isEmpty()) {

            //current vertex distance will not change so add it to distance map
            vertexDistances.put(current, unvisitedVertices.get(current));

            //check neighbours of current vertex
            for (Edge neighbour : current.getNeighbours()) {

                //check neighbour is unvisited
                if (unvisitedVertices.containsKey(neighbour.destination)) {

                    //potentially shorter distance of current vertex distance plus edge weight to neighbour
                    Integer proposalDistance = unvisitedVertices.get(current) + neighbour.weight;

                    //if shorter then replace
                    if (proposalDistance < unvisitedVertices.get(neighbour.destination)) {
                        unvisitedVertices.put(neighbour.destination, proposalDistance);
                    }
                }
            }

            //remove current vertex from visited vertices
            unvisitedVertices.remove(current);

            //new current vertex is next minimum weighted vertex
            current = getMinVertex(unvisitedVertices);
        }

        return vertexDistances;
    }

}
