package com.lun.algorithms4th.c4.graph;

import java.io.File;

import com.lun.algorithms4th.util.In;


public class GraphClient {
	// maximum degree
	public static int maxDegree(Graph G) {
		int max = 0;
		for (int v = 0; v < G.getNumOfVertex(); v++)
			if (G.degree(v) > max)
				max = G.degree(v);
		return max;
	}

	// average degree
	public static int avgDegree(Graph G) {
		// each edge incident on two vertices
		return 2 * G.getNumOfEdge() / G.getNumOfVertex();
	}

	// number of self-loops
	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for (int v = 0; v < G.getNumOfVertex(); v++)
			for (int w : G.adjacent(v))
				if (v == w)
					count++;
		return count / 2; // self loop appears in adjacency list twice
	}

	public static void main(String[] args) {
		In in = new In(new File("./src/main/resources/tinyG.txt"));
		Graph G = new Graph(in);
		System.out.println(G);

		System.out.println("vertex of maximum degree = " + maxDegree(G));
		System.out.println("average degree           = " + avgDegree(G));
		System.out.println("number of self loops     = " + numberOfSelfLoops(G));

	}
}
