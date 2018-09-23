/******************************************************************************
 *  Compilation:  javac DepthFirstPaths.java
 *  Execution:    java DepthFirstPaths G s
 *  Dependencies: Graph.java Stack.java System.out.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyCG.txt
 *                https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  Run depth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *
 *  %  java Graph tinyCG.txt
 *  6 8
 *  0: 2 1 5 
 *  1: 0 2 
 *  2: 0 1 3 4 
 *  3: 5 4 2 
 *  4: 3 2 
 *  5: 3 0 
 *
 *  % java DepthFirstPaths tinyCG.txt 0
 *  0 to 0:  0
 *  0 to 1:  0-2-1
 *  0 to 2:  0-2
 *  0 to 3:  0-2-3
 *  0 to 4:  0-2-3-4
 *  0 to 5:  0-2-3-5
 *
 ******************************************************************************/

package com.lun.algorithms4th.c4.graph;

import java.io.File;

import com.lun.algorithms4th.c1.fundamental.Stack;
import com.lun.algorithms4th.util.In;


/**
 * The {@code DepthFirstPaths} class represents a data type for finding paths
 * from a source vertex <em>s</em> to every other vertex in an undirected graph.
 * <p>
 * This implementation uses depth-first search. The constructor takes time
 * proportional to <em>V</em> + <em>E</em>, where <em>V</em> is the number of
 * vertices and <em>E</em> is the number of edges. Each call to
 * {@link #hasPathTo(int)} takes constant time; each call to
 * {@link #pathTo(int)} takes time proportional to the length of the path. It
 * uses extra space (not including the graph) proportional to <em>V</em>.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class DepthFirstPaths {
	private boolean[] marked; // marked[v] = is there an s-v path?
	private int[] edgeTo; // edgeTo[v] = last edge on s-v path
	private final int sourceVertex; // source vertex

	/**
	 * Computes a path between {@code s} and every other vertex in graph {@code G}.
	 * 
	 * @param G
	 *            the graph
	 * @param sourceVertex
	 *            the source vertex
	 * @throws IllegalArgumentException
	 *             unless {@code 0 <= s < V}
	 */
	public DepthFirstPaths(Graph G, int sourceVertex) {
		this.sourceVertex = sourceVertex;
		edgeTo = new int[G.getNumOfVertex()];
		marked = new boolean[G.getNumOfVertex()];
		validateVertex(sourceVertex);
		dfs(G, sourceVertex);
	}

	// depth first search from v
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adjacent(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}

	/**
	 * Is there a path between the source vertex {@code s} and vertex {@code v}?
	 * 
	 * @param vertex
	 *            the vertex
	 * @return {@code true} if there is a path, {@code false} otherwise
	 * @throws IllegalArgumentException
	 *             unless {@code 0 <= v < V}
	 */
	public boolean hasPathTo(int vertex) {
		validateVertex(vertex);
		return marked[vertex];
	}

	/**
	 * Returns a path between the source vertex {@code s} and vertex {@code v}, or
	 * {@code null} if no such path.
	 * 
	 * @param vertex
	 *            the vertex
	 * @return the sequence of vertices on a path between the source vertex
	 *         {@code s} and vertex {@code v}, as an Iterable
	 * @throws IllegalArgumentException
	 *             unless {@code 0 <= v < V}
	 */
	public Iterable<Integer> pathTo(int vertex) {
		validateVertex(vertex);
		if (!hasPathTo(vertex))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = vertex; x != sourceVertex; x = edgeTo[x])
			path.push(x);
		path.push(sourceVertex);
		return path;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int vertex) {
		int V = marked.length;
		if (vertex < 0 || vertex >= V)
			throw new IllegalArgumentException("vertex " + vertex + " is not between 0 and " + (V - 1));
	}

	/**
	 * Unit tests the {@code DepthFirstPaths} data type.
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {
		In in = new In(new File("./src/main/resources/tinyCG.txt"));
		Graph G = new Graph(in);
		int s = Integer.parseInt("0");
		DepthFirstPaths dfs = new DepthFirstPaths(G, s);

		for (int v = 0; v < G.getNumOfVertex(); v++) {
			if (dfs.hasPathTo(v)) {
				System.out.printf("%d to %d:  ", s, v);
				for (int x : dfs.pathTo(v)) {
					if (x == s)
						System.out.print(x);
					else
						System.out.print("-" + x);
				}
				System.out.println();
			}

			else {
				System.out.printf("%d to %d:  not connected\n", s, v);
			}

		}
	}

}

/******************************************************************************
 * Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 * This file is part of algs4.jar, which accompanies the textbook
 *
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 *
 *
 * algs4.jar is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * algs4.jar is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * algs4.jar. If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
