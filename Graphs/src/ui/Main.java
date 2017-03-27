package ui;

import java.util.Arrays;

import bp.Edge;
import bp.Graph;
import bp.Vertex;

public class Main {

	public static void main(String[] args) {
		System.out.println("Graph One...");
		// New Graph
		Graph myGraph = new Graph();

		Vertex vertexA = new Vertex('A');
		Vertex vertexB = new Vertex('B');
		Vertex vertexC = new Vertex('C');
		Vertex vertexD = new Vertex('D');
		Vertex vertexE = new Vertex('E');
		Vertex vertexF = new Vertex('F');
		Vertex vertexG = new Vertex('G');

		// (Constructor Change)
		myGraph.addEdge(new Edge(vertexA, vertexB, 3));
		myGraph.addEdge(new Edge(vertexA, vertexC, 11));
		myGraph.addEdge(new Edge(vertexA, vertexG, 1));
		myGraph.addEdge(new Edge(vertexA, vertexF, 3));
		myGraph.addEdge(new Edge(vertexG, vertexF, 1));
		myGraph.addEdge(new Edge(vertexE, vertexC, 15));
		myGraph.addEdge(new Edge(vertexD, vertexE, 5));
		myGraph.addEdge(new Edge(vertexD, vertexC, 5));
		myGraph.addEdge(new Edge(vertexB, vertexC, 5));

		// //(No Constructor Change)
		// myGraph.addEdge(new Edge(vertexA, vertexB));
		// myGraph.getEdges().get(0).setWeight(3);
		// myGraph.addEdge(new Edge(vertexA, vertexC));
		// myGraph.getEdges().get(1).setWeight(11);
		// myGraph.addEdge(new Edge(vertexA, vertexG));
		// myGraph.getEdges().get(2).setWeight(1);
		// myGraph.addEdge(new Edge(vertexA, vertexF));
		// myGraph.getEdges().get(3).setWeight(3);
		// myGraph.addEdge(new Edge(vertexG, vertexF));
		// myGraph.getEdges().get(4).setWeight(1);
		// myGraph.addEdge(new Edge(vertexE, vertexC));
		// myGraph.getEdges().get(5).setWeight(15);
		// myGraph.addEdge(new Edge(vertexD, vertexE));
		// myGraph.getEdges().get(6).setWeight(5);
		// myGraph.addEdge(new Edge(vertexD, vertexC));
		// myGraph.getEdges().get(7).setWeight(5);
		// myGraph.addEdge(new Edge(vertexB, vertexC));
		// myGraph.getEdges().get(8).setWeight(5);

		// Test One
		System.out.print(
				"What is the Shortest Path From F to E (Least Weighted Path) (Answer should be - F, G, A, B, C, D, E). Your Answer: ");
		char[] path = myGraph.getShortestPath('F', 'E');
		if (path != null) {
			for (char c : path) {
				System.out.print(c + ", ");
			}
		} else {
			System.out.print("No Path Returned...");
		}

		System.out.print("\nWhat is a valid Euler Cycle on this Graph? ");
		char[] euler = myGraph.StrongEulerCycle('A');
		if (euler != null) {
			for (char c : euler) {
				System.out.print(c + ", ");
			}
		} else {
			System.out.print("No Path Returned...");
		}

		System.out.println("\nIs this a connected Graph? (Should be true) --> " + myGraph.isConnected());

		System.out.print(
				"What are the adjacencies of Vertex C? (Answer should include A, B, D, and E). Your Answer: ");
		char[] adj = myGraph.getAdjacentVertices('C');
		if (adj != null) {
			for (char c : adj) {
				System.out.print(c + ", ");
			}
		} else {
			System.out.print("No Vertices Returned");
		}

		System.out.print("\nIs this a complete graph? (Should be false) --> " + myGraph.isComplete());

		// New Graph
		myGraph = new Graph();
		System.out.println("\n\nGraph Two...");

		// Declare Vertices
		vertexA = new Vertex('A');
		vertexB = new Vertex('B');
		vertexC = new Vertex('C');
		vertexD = new Vertex('D');
		vertexE = new Vertex('E');
		vertexF = new Vertex('F');
		vertexG = new Vertex('G');

		// Add Vertices
		myGraph.addVertex(vertexA);
		myGraph.addVertex(vertexB);
		myGraph.addVertex(vertexC);
		myGraph.addVertex(vertexD);
		myGraph.addVertex(vertexE);
		myGraph.addVertex(vertexF);
		myGraph.addVertex(vertexG);

		// Add Edges (Constructor Change)
		myGraph.addEdge(new Edge(vertexC, vertexA, 1));
		myGraph.addEdge(new Edge(vertexC, vertexB, 1));
		myGraph.addEdge(new Edge(vertexC, vertexD, 1));
		myGraph.addEdge(new Edge(vertexC, vertexE, 1));
		myGraph.addEdge(new Edge(vertexC, vertexE, 1));
		myGraph.addEdge(new Edge(vertexC, vertexG, 1));
		myGraph.addEdge(new Edge(vertexA, vertexB, 1));
		myGraph.addEdge(new Edge(vertexB, vertexD, 1));
		myGraph.addEdge(new Edge(vertexB, vertexD, 1));
		myGraph.addEdge(new Edge(vertexD, vertexE, 1));
		myGraph.addEdge(new Edge(vertexE, vertexF, 1));
		myGraph.addEdge(new Edge(vertexF, vertexG, 1));
		myGraph.addEdge(new Edge(vertexG, vertexA, 1));
		myGraph.addEdge(new Edge(vertexG, vertexA, 1));

		// // Add Edges (No Constructor Change)
		// myGraph.addEdge(new Edge(vertexC, vertexA));
		// myGraph.addEdge(new Edge(vertexC, vertexB));
		// myGraph.addEdge(new Edge(vertexC, vertexD));
		// myGraph.addEdge(new Edge(vertexC, vertexE));
		// myGraph.addEdge(new Edge(vertexC, vertexE));
		// myGraph.addEdge(new Edge(vertexC, vertexG));
		// myGraph.addEdge(new Edge(vertexA, vertexB));
		// myGraph.addEdge(new Edge(vertexB, vertexD));
		// myGraph.addEdge(new Edge(vertexB, vertexD));
		// myGraph.addEdge(new Edge(vertexD, vertexE));
		// myGraph.addEdge(new Edge(vertexE, vertexF));
		// myGraph.addEdge(new Edge(vertexF, vertexG));
		// myGraph.addEdge(new Edge(vertexG, vertexA));
		// myGraph.addEdge(new Edge(vertexG, vertexA));

		System.out.print("\nWhat is a valid Euler Cycle on this Graph? ");
		euler = myGraph.StrongEulerCycle('A');
		if (euler != null) {
			for (char c : euler) {
				System.out.print(c + ", ");
			}
		} else {
			System.out.print("No Path Returned...");
		}

		System.out.println("\nIs this a connected Graph? (Should be true) --> " + myGraph.isConnected());

		System.out.print(
				"What are the adjacencies of Vertex C? (Answer should include A, B, D, E, and G). Your Answer: ");
		adj = myGraph.getAdjacentVertices('C');
		if (adj != null) {
			for (char c : adj) {
				System.out.print(c + ", ");
			}
		} else {
			System.out.print("No Vertices Returned");
		}

		System.out.print("\nIs this a complete graph? (Should be false) --> " + myGraph.isComplete());
	}
}
