package com.lun.algorithms4th.c4.graph;

import java.io.File;
import java.util.NoSuchElementException;

import com.lun.algorithms4th.c1.fundamental.Bag;
import com.lun.algorithms4th.c1.fundamental.Stack;
import com.lun.algorithms4th.util.In;


/**
 * The {@code Graph} class represents an undirected graph of vertices named 0
 * through <em>V</em> – 1. It supports the following two primary operations: add
 * an edge to the graph, iterate over all of the vertices adjacent to a vertex.
 * It also provides methods for returning the number of vertices <em>V</em> and
 * the number of edges <em>E</em>. Parallel edges and self-loops are permitted.
 * By convention, a self-loop <em>v</em>-<em>v</em> appears in the adjacency
 * list of <em>v</em> twice and contributes two to the degree of <em>v</em>.
 * <p>
 * This implementation uses an adjacency-lists representation, which is a
 * vertex-indexed array of {@link Bag} objects. All operations take constant
 * time (in the worst case) except iterating over the vertices adjacent to a
 * given vertex, which takes time proportional to the number of such vertices.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int numOfVertex;//顶点的数目
	private int numOfEdge;
	private Bag<Integer>[] adjacent;

	/**
	 * Initializes an empty graph with {@code V} vertices and 0 edges. param V the
	 * number of vertices
	 *
	 * @param numOfVertex
	 *            number of vertices
	 * @throws IllegalArgumentException
	 *             if {@code V < 0}
	 */
	@SuppressWarnings("unchecked")
	public Graph(int numOfVertex) {
		if (numOfVertex < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.numOfVertex = numOfVertex;
		this.numOfEdge = 0;
		adjacent = (Bag<Integer>[]) new Bag[numOfVertex];
		for (int v = 0; v < numOfVertex; v++) {
			adjacent[v] = new Bag<Integer>();
		}
	}

	/**
	 * Initializes a graph from the specified input stream. The format is the number
	 * of vertices <em>V</em>, followed by the number of edges <em>E</em>, followed
	 * by <em>E</em> pairs of vertices, with each entry separated by whitespace.
	 *
	 * @param in
	 *            the input stream
	 * @throws IllegalArgumentException
	 *             if the endpoints of any edge are not in prescribed range
	 * @throws IllegalArgumentException
	 *             if the number of vertices or edges is negative
	 * @throws IllegalArgumentException
	 *             if the input stream is in the wrong format
	 */
	@SuppressWarnings("unchecked")
	public Graph(In in) {
		try {
			this.numOfVertex = in.readInt();
			if (numOfVertex < 0)
				throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
			adjacent = (Bag<Integer>[]) new Bag[numOfVertex];
			for (int v = 0; v < numOfVertex; v++) {
				adjacent[v] = new Bag<Integer>();
			}
			int E = in.readInt();
			if (E < 0)
				throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Graph constructor", e);
		}
	}

	/**
	 * Initializes a new graph that is a deep copy of {@code G}.
	 *
	 * @param G
	 *            the graph to copy
	 */
	public Graph(Graph G) {
		this(G.getNumOfVertex());
		this.numOfEdge = G.getNumOfEdge();
		for (int v = 0; v < G.getNumOfVertex(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adjacent[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adjacent[v].add(w);
			}
		}
	}

	/**
	 * Returns the number of vertices in this graph.
	 *
	 * @return the number of vertices in this graph
	 */
	public int getNumOfVertex() {
		return numOfVertex;
	}

	/**
	 * Returns the number of edges in this graph.
	 *
	 * @return the number of edges in this graph
	 */
	public int getNumOfEdge() {
		return numOfEdge;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if (v < 0 || v >= numOfVertex)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (numOfVertex - 1));
	}

	/**
	 * Adds the undirected edge v-w to this graph.
	 *
	 * @param v
	 *            one vertex in the edge
	 * @param w
	 *            the other vertex in the edge
	 * @throws IllegalArgumentException
	 *             unless both {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		numOfEdge++;
		adjacent[v].add(w);
		adjacent[w].add(v);
	}

	/**
	 * Returns the vertices adjacent to vertex {@code v}.
	 *
	 * @param v
	 *            the vertex
	 * @return the vertices adjacent to vertex {@code v}, as an iterable
	 * @throws IllegalArgumentException
	 *             unless {@code 0 <= v < V}
	 */
	public Iterable<Integer> adjacent(int v) {
		validateVertex(v);
		return adjacent[v];
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param v
	 *            the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException
	 *             unless {@code 0 <= v < V}
	 */
	public int degree(int v) {
		validateVertex(v);
		return adjacent[v].size();
	}

	/**
	 * Returns a string representation of this graph.
	 *
	 * @return the number of vertices <em>V</em>, followed by the number of edges
	 *         <em>E</em>, followed by the <em>V</em> adjacency lists
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(numOfVertex + " vertices, " + numOfEdge + " edges " + NEWLINE);
		for (int v = 0; v < numOfVertex; v++) {
			s.append(v + ": ");
			for (int w : adjacent[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Unit tests the {@code Graph} data type.
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {
		In in = new In(new File("./src/main/resources/tinyG.txt"));
		Graph G = new Graph(in);
		System.out.println(G);
	}

}
