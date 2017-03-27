package bp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph implements IGraph2 {

	/* You will need to extend IGraph2 for the second lab two */

	List<Edge> edges = new ArrayList<Edge>();
	List<Vertex> vertices = new ArrayList<Vertex>();
	List<Edge> surroundingEdges = new ArrayList<Edge>();
	public boolean checkIfConnected = false;
	public static List<Character> totalVertices = new ArrayList<Character>();
	private List<Vertex> eulerPath = new ArrayList<Vertex>();
	private List<Vertex> hamiltonianPath = new ArrayList<Vertex>();
	private List<Vertex> hamiltonianCycle = new ArrayList<Vertex>();
	private List<List> shortestPathTotalList = new ArrayList<List>();
	private Vertex startingVertex = null;
	private Vertex endingVertex = null;
	private boolean firstIteration = true;

	@Override
	public Vertex getVertexByID(char pID) {
		for (int n = 0; n < vertices.size(); n++) {
			if (vertices.get(n).getID() == pID)
				return vertices.get(n);
		}
		return null;
	}

	@Override
	public List<Edge> getEdges() {
		return edges;
	}

	@Override
	public void setEdges(List<Edge> pEdges) {
		edges = pEdges;
	}

	@Override
	public List<Vertex> getVertices() {
		return vertices;
	}

	public int getTotalNumberOfVertices() {
		return totalVertices.size();
	}

	@Override
	public void setVertices(List<Vertex> pVertices) {
		vertices = pVertices;
	}

	@Override
	public void addEdge(Edge pEdge) {
		boolean check1 = false;
		boolean check2 = false;
		for (int n = 0; n < vertices.size(); n++) {
			if (vertices.get(n) == pEdge.getVertex1()) {
				check1 = true;
			}
			if (vertices.get(n) == pEdge.getVertex2()) {
				check2 = true;
			}
		}
		if (check1) {

		} else {
			if (!vertices.contains(pEdge.getVertex1()))
				vertices.add(pEdge.getVertex1());

		}
		if (check2) {

		} else {
			if (!vertices.contains(pEdge.getVertex2()))
				vertices.add(pEdge.getVertex2());
		}
		edges.add(pEdge);
	}

	@Override
	public void removeEdge(int pIndex) {
		if (edges.size() <= pIndex) {
			System.out.println("Graph does not contain that edge.");
			return;
		} else
			edges.remove(pIndex);
	}

	@Override
	public void addVertex(Vertex pVertex) {
		vertices.add(pVertex);
	}

	@Override
	public void removeVertex(int pIndex) {

		if ((pIndex >= vertices.size()) && (pIndex < totalVertices.size())) {
			if (vertices.size() == totalVertices.size())
				totalVertices.remove(pIndex);
		} else if (vertices.size() < pIndex) {
			System.out.println("Graph does not contain that vertex.");
			return;
		} else {
			Vertex toRemove = vertices.get(pIndex);
			for (int n = 0; n < edges.size(); n++) {
				if (edges.get(n).getVertex1() == toRemove
						|| edges.get(n).getVertex2() == toRemove) {
					edges.remove(edges.get(n));
					n--;
				}
			}
			totalVertices.remove(pIndex);
			vertices.remove(pIndex);
		}
	}
	
	private char[] trim(char[] pCharArray) {
		List<Character> charList = new ArrayList<Character>();
		char[] arrayToReturn;
		for (int n = 0; n < pCharArray.length; n++) {
			if (pCharArray[n] != 0)
			charList.add(pCharArray[n]);
		}
		arrayToReturn = new char[charList.size()];
		for (int i = 0; i < charList.size(); i++) {
			arrayToReturn[i] = charList.get(i);
		}
			
		return arrayToReturn;
	}

	@Override
	public boolean isConnected() {
		if (vertices.size() == 0) {
			return true;
		}
		char[] list = trim(DepthFirstSearch());
		if (list.length == vertices.size()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isComplete() {
		List<Vertex> toCompare = new ArrayList<Vertex>();
		List<Vertex> connectedVertices = new ArrayList<Vertex>();
		List<Boolean> check = new ArrayList<Boolean>();

		/* DOES NOT ACCOUNT FOR LOOPS */
		
		for (Vertex v : vertices) {
			connectedVertices = getSingularAdjacentVertices(v);
			if (connectedVertices.size() == vertices.size() - 1) {
				//nothing
			} else {
				return false;
			}
		}
		
		
		return true;

//		if (vertices.size() > 0) {
//			for (int n = 0; n < vertices.size(); n++) {
//				toCompare = getAdjacentVertices(vertices.get(n));
//				if (toCompare.size() == vertices.size() - 1)
//					check.add(true);
//				else
//					check.add(false);
//				toCompare.clear();
//			}
//			if (check.size() != totalVertices.size())
//				return false;
//
//			for (int n = 0; n < check.size(); n++) {
//				if (check.get(n) == true) {
//
//				} else {
//					return false;
//				}
//			}
//		} else {
//			return true;
//		}
//		return true;
	}
	
	private List<Vertex> getSingularAdjacentVertices(Vertex pVertex) {
		List<Vertex> verticesToReturn = new ArrayList<Vertex>();
		
		for (Edge e : edges) {
			if (e.getVertex1() == pVertex) {
				if (!verticesToReturn.contains(e.getVertex2())) {
					verticesToReturn.add(e.getVertex2());
				}
			} else if (e.getVertex2() == pVertex) {
				if (!verticesToReturn.contains(e.getVertex1())) {
					verticesToReturn.add(e.getVertex1());
				}
			} else {
				
			}
		}
			
		return verticesToReturn;
	}

	public char[] DepthFirstSearch() {
		Vertex currentVertex = null;
		Stack<Vertex> pathTraveled = new Stack<Vertex>();
		char[] verticesVisited = new char[vertices.size()];
		List<Vertex> connectedVertices = new ArrayList<Vertex>();
		checkIfConnected = false;
		boolean previous = false;
		int counterForVisited = 0;

		if (vertices.size() == 0) {
			return new char[0];
		}

		currentVertex = vertices.get(0);
		do {
			if (previous) {
				previous = false;
				pathTraveled.pop();
				if (pathTraveled.size() != 0)
					currentVertex = pathTraveled.peek();
			}
			connectedVertices = getAdjacentVertices(currentVertex);
			boolean check = false;
			for (int n = 0; n < pathTraveled.size(); n++) {
				if (pathTraveled.peek() == currentVertex)
					check = true;
			}
			if (!check && !currentVertex.getHasVisited())
				pathTraveled.push(currentVertex);
			currentVertex.setHasVisited(true);

			for (int i = 0; i < verticesVisited.length; i++) {
				if (verticesVisited[i] == currentVertex.getID()) {
					break;
				}
				if (i == verticesVisited.length - 1) {
					verticesVisited[counterForVisited++] = currentVertex
							.getID();
				}
			}
			for (int n = 0; n < connectedVertices.size(); n++) {
				if (connectedVertices.get(n).getHasVisited()) {
					// Look at next connected vertex
				} else {
					currentVertex = connectedVertices.get(n);
					break;
				}
				if (n == connectedVertices.size() - 1) {
					previous = true;
				}
			}
			if (-1 == connectedVertices.size() - 1) {
				previous = true;
			}
		} while (!pathTraveled.isEmpty());
		// reset values of all vertices back to false
		for (int n = 0; n < vertices.size(); n++) {
			vertices.get(n).setHasVisited(false);
		}

		return verticesVisited;
	}

	public List<Character> searchWithDepthFirst(Vertex pStart) {
		/*
		 * Use recursion
		 */

		List<Character> visitedVertices = new ArrayList<Character>();

		startingVertex = pStart;
		firstIteration = true;
		eulerPath = null;

		eulerRecursion(null, null, pStart, edges);

		for (Vertex v : eulerPath) {
			visitedVertices.add(v.getID());
		}

		return visitedVertices;
	}

	private void eulerRecursion(List<Vertex> pVertexPath, List<Edge> pEdgePath,
			Vertex pCurrentVertex, List<Edge> pEdgesRemaining) {
		List<Edge> connectedEdges = new ArrayList<Edge>();
		List<Edge> edgePathTraveled = new ArrayList<Edge>();
		List<Vertex> vertexPathTraveled = new ArrayList<Vertex>();
		List<Edge> totalEdgesRemaining = new ArrayList<Edge>();
		Vertex currentVertex = pCurrentVertex;
		Vertex previousVertex = pCurrentVertex;
		boolean toReturn = false;

		// see if the path has been set, if so we don't need to do anything else
		if (eulerPath != null) {
			return;
		}

		// if its the first iteration manually set value, else use parameter
		// values
		if (firstIteration)
			vertexPathTraveled.add(startingVertex);
		if (!firstIteration) {
			edgePathTraveled.addAll(pEdgePath);
			vertexPathTraveled.addAll(pVertexPath);
		}
		// get unvisited edges + connected edges to current vertex
		totalEdgesRemaining.addAll(pEdgesRemaining);
		connectedEdges.addAll(getAdjacentEdges(pCurrentVertex));
		do {
			// if not the first iteration, back where we started, and there are
			// no remaing unvisited vertices
			// set the path!
			if (!firstIteration && totalEdgesRemaining.size() == 0
					&& currentVertex == startingVertex) {
				if (eulerPath == null)
					eulerPath = vertexPathTraveled;
				toReturn = false;
			} else {
				// otherwise..
				firstIteration = false;
				toReturn = false;
				// for each connected edge to vertex
				for (int n = 0; n < connectedEdges.size(); n++) {
					Edge e = connectedEdges.get(n);
					// check if visited
					if (totalEdgesRemaining.contains(e)) {
						if (e.getVertex1() == currentVertex) {
							currentVertex = e.getVertex2();
						} else {
							currentVertex = e.getVertex1();
						}
						// accounts for multiple edges connected to the same
						// vertices
						// if the edge has been visited move to next edge,
						if (!totalEdgesRemaining
								.contains(findUnusedConnectedEdge(
										currentVertex, previousVertex))) {
							currentVertex = previousVertex;
						} else {
							// otherwise set edge to traveled
							e.setTraveled(true);
							// if it is the last edge remaining, then set edge +
							// vertex path
							// and set the do-while loop to run again, so we can
							// set the eulerPath
							if (currentVertex == startingVertex
									&& totalEdgesRemaining.size() == 1) {
								totalEdgesRemaining.remove(e);
								edgePathTraveled.add(e);
								vertexPathTraveled.add(currentVertex);
								toReturn = true;
							} else {
								// otherwise add edge and recurse on the next
								// vertex
								totalEdgesRemaining.remove(e);
								edgePathTraveled.add(e);
								vertexPathTraveled.add(currentVertex);
								eulerRecursion(vertexPathTraveled,
										edgePathTraveled, currentVertex,
										totalEdgesRemaining);
								// this is to see if it's made a mistake and
								// needs to go back
								// it will remove the edge and vertex it just
								// tried. For some reason
								// when we backed up the lists' values would
								// carry over to the previous iteration
								// this is to fix it if it made a mistake,
								// otherwise just exit because
								// the path has been found.
								if (eulerPath == null) {
									e.traveled = false;
									totalEdgesRemaining.add(e);
									edgePathTraveled.remove(edgePathTraveled
											.size() - 1);
									vertexPathTraveled
											.remove(vertexPathTraveled.size() - 1);
								}
							}
						}
					}
				}
			}
		} while (toReturn);
	}

	public char[] BreadthFirstSearch() {
		Queue<Vertex> toVisit = new LinkedList<Vertex>();
		List<Character> tempList = new ArrayList<Character>();
		char[] verticesToReturn;
		List<Vertex> connectedVertices = new ArrayList<Vertex>();
		Vertex currentVertex;
		int arrayCounter = 0;

		if (vertices.size() == 0) {
			return new char[0];
		}

		currentVertex = vertices.get(0);
		connectedVertices = getAdjacentVertices(vertices.get(0));
		tempList.add(vertices.get(0).getID());
		vertices.get(0).setHasVisited(true);
		toVisit.addAll(connectedVertices);
		for (int n = 0; n < connectedVertices.size(); n++) {
			tempList.add(connectedVertices.get(n).getID());
		}
		do {
			currentVertex = toVisit.peek();
			toVisit.remove();
			if (!currentVertex.getHasVisited()) {
				connectedVertices = getAdjacentVertices(currentVertex);
				for (int n = 0; n < connectedVertices.size(); n++) {
					if (!connectedVertices.get(n).getHasVisited()
							&& !toVisit.contains(connectedVertices.get(n))) {
						toVisit.add(connectedVertices.get(n));
						if (!tempList
								.contains(connectedVertices.get(n).getID()))
							tempList.add(connectedVertices.get(n).getID());
					}
				}
				currentVertex.setHasVisited(true);
			}

		} while (!toVisit.isEmpty());
		for (int n = 0; n < vertices.size(); n++) {
			vertices.get(n).setHasVisited(false);
		}
		verticesToReturn = new char[tempList.size()];
		for (char c : tempList) {
			verticesToReturn[arrayCounter++] = c;
		}

		return verticesToReturn;
	}

	@Override
	public boolean isAdjacent(char pVertex1ID, char pVertex2ID) {
		boolean check1 = false;
		boolean check2 = false;
		int counter = 0;
		while (counter < edges.size()) {
			check1 = false;
			check2 = false;
			if (edges.get(counter).getVertex1().getID() == pVertex1ID
					|| edges.get(counter).getVertex2().getID() == pVertex1ID) {
				check1 = true;
			}
			if (edges.get(counter).getVertex1().getID() == pVertex2ID
					|| edges.get(counter).getVertex2().getID() == pVertex2ID) {
				check2 = true;
			}
			if (check1 && check2) {
				return true;
			}
			counter++;
		}
		return false;
	}

	@Override
	public char[] getAdjacentVertices(char pVertexID) {
		ArrayList<Character> tempList = new ArrayList<Character>();
		char[] arrayToReturn;

		for (Edge e : edges) {
			if (e.getVertex1().getID() == pVertexID) {
				tempList.add(e.getVertex2().getID());
			} else if (e.getVertex2().getID() == pVertexID) {
				tempList.add(e.getVertex1().getID());
			}
		}
		arrayToReturn = new char[tempList.size()];
		for (int n = 0; n < arrayToReturn.length; n++) {
			arrayToReturn[n] = tempList.get(n);
		}
		return arrayToReturn;
	}

	public List<Vertex> getAdjacentVertices(Vertex pVertex) {
		List<Vertex> toReturn = new ArrayList<Vertex>();

		for (Edge e : edges) {
			if (e.getVertex1() == pVertex) {
				toReturn.add(e.getVertex2());
			} else if (e.getVertex2() == pVertex) {
				toReturn.add(e.getVertex1());
			}
		}
		return toReturn;
	}

	public List<Edge> getAdjacentEdges(Vertex pVertex) {
		surroundingEdges.clear();
		for (int n = 0; n < edges.size(); n++) {
			if (edges.get(n).getVertex1() == pVertex) {
				surroundingEdges.add(edges.get(n));
			}
			if (edges.get(n).getVertex2() == pVertex) {
				surroundingEdges.add(edges.get(n));
			}
		}
		return surroundingEdges;
	}

	public Edge getConnectedEdge(Vertex pVertex1, Vertex pVertex2) {
		boolean isConnected = false;
		@SuppressWarnings("unused")
		List<Vertex> connectedVertices = new ArrayList<Vertex>();
		if (isAdjacent(pVertex1.getID(), pVertex2.getID()))
			isConnected = true;
		if (isConnected) {
			connectedVertices = getAdjacentVertices(pVertex1);

			if (pVertex1 == pVertex2) {
				for (Edge e : edges) {
					if (e.isLoop
							&& (e.getVertex1() == pVertex1 || e.getVertex2() == pVertex1)
							&& (e.getVertex1() == pVertex2 || e.getVertex2() == pVertex2)) {
						return e;
					}
				}
			}
			for (Edge e : edges) {
				if (e.isLoop) {
					// do nothing and move to the next one
				} else {
					if ((e.getVertex1() == pVertex1 || e.getVertex1() == pVertex2)
							&& (e.getVertex2() == pVertex1 || e.getVertex2() == pVertex2)) {
						return e;
					}
				}
			}
		} else {
			return null;
		}
		return null;
	}

	private Vertex findVertex(char pCharToFind) {
		for (Vertex v : vertices) {
			if (v.getID() == pCharToFind) {
				return v;
			}
		}
		return null;
	}

	private Edge findUnusedConnectedEdge(Vertex pVertex1, Vertex pVertex2) {
		boolean isConnected = false;
		@SuppressWarnings("unused")
		List<Vertex> connectedVertices = new ArrayList<Vertex>();
		if (isAdjacent(pVertex1.getID(), pVertex2.getID()))
			isConnected = true;
		if (isConnected) {
			connectedVertices = getAdjacentVertices(pVertex1);

			if (pVertex1 == pVertex2) {
				for (Edge e : edges) {
					if (e.isLoop
							&& (e.getVertex1() == pVertex1 || e.getVertex2() == pVertex1)
							&& (e.getVertex1() == pVertex2 || e.getVertex2() == pVertex2)) {
						if (e.traveled) {
							// do nothing
						} else
							return e;
					}
				}
			}
			for (Edge e : edges) {
				if (e.isLoop) {
					// do nothing and move to the next one
				} else {
					if ((e.getVertex1() == pVertex1 || e.getVertex1() == pVertex2)
							&& (e.getVertex2() == pVertex1 || e.getVertex2() == pVertex2)) {
						if (e.traveled) {
							// do nothing
						} else
							return e;
					}
				}
			}
		} else {
			return null;
		}
		return null;
	}
	
	
	/**
	 * Return a path of vertices that is shortest from pVertex1ID
	 * to pVertex2ID.
	 * 
	 * @param pVertex1ID
	 * 		 The start vertex
	 * @param pVertex2ID 
	 * 		 The end vertex
	 * @return char[]
	 */
	public char[] getShortestPath(char pVertex1ID, char pVertex2ID) {
		startingVertex = getVertexByID(pVertex1ID);
		endingVertex = getVertexByID(pVertex2ID);
		Vertex currentVertex;
		Vertex previousVertex = null;
		int shortestPathNumber = 0;
		int placeHolder = 0;
		int tempPath = 0;
		char[] arrayToReturn;
		shortestPathTotalList.clear();
		firstIteration = true;
		shortestPathRecursion(null, startingVertex);
		
		
		if (startingVertex == null || endingVertex == null) return new char[0];
		for (int n = 0; n < shortestPathTotalList.size(); n++) {
			List<Vertex> tempList = new ArrayList<Vertex>();
			tempList.addAll(shortestPathTotalList.get(n));
			tempPath = 0;
			for (int i = 0; i < tempList.size(); i++) {
				if (i == 0) {
					previousVertex = tempList.get(i);
					currentVertex = tempList.get(i);
				} else {
					currentVertex = tempList.get(i);
					tempPath += getWeightBetweenVertices(currentVertex, previousVertex);
				}
				
				
				//save this for last for next iteration
				previousVertex = tempList.get(i);
			}
			if (n == 0) 
				shortestPathNumber = tempPath;
			else if (shortestPathNumber > tempPath) {
				shortestPathNumber = tempPath;
				placeHolder = n;
			}
		}
		List<Vertex> returnList = shortestPathTotalList.get(placeHolder);
		arrayToReturn = new char[returnList.size()];
		for (int n = 0; n < returnList.size(); n++) {
			arrayToReturn[n] = returnList.get(n).getID();
		}
		
		return arrayToReturn;
	}
	
	private void shortestPathRecursion(List<Vertex> pVertexPath, Vertex pCurrentVertex) {
		//get every possible path, and then add each individual path to shortestPathTotalList.
		//when the recursion ends it will look through that list and compare each path to find the shortest.
		//just need to work on this list.
		List<Vertex> vertexPath = new ArrayList<Vertex>();
		List<Vertex> connectedVertices = new ArrayList<Vertex>();
		Vertex currentVertex = pCurrentVertex;
		boolean toReturn = false;
		
		connectedVertices.addAll(getAdjacentVertices(currentVertex));
		if (pVertexPath != null) vertexPath.addAll(pVertexPath);
		
		
		if (firstIteration)
			vertexPath.add(currentVertex);
		if (!firstIteration && currentVertex == endingVertex) {
			List<Vertex> tempList = new ArrayList<Vertex>();
			tempList.addAll(vertexPath);
			shortestPathTotalList.add(tempList);
			return;
		}
		do {
			firstIteration = false;
			for (int n = 0; n < connectedVertices.size(); n++) {
				currentVertex = connectedVertices.get(n);
				if (currentVertex == endingVertex) {
					vertexPath.add(currentVertex);
					List<Vertex> tempList = new ArrayList<Vertex>();
					tempList.addAll(vertexPath);
					shortestPathTotalList.add(tempList);
					vertexPath.remove(vertexPath.size() - 1);
				} else if (vertexPath.contains(currentVertex)) {
					
				} else {
					vertexPath.add(currentVertex);
					int size = vertexPath.size();
					shortestPathRecursion(vertexPath, currentVertex);
					if (size == vertexPath.size()) {
						vertexPath.remove(vertexPath.size() - 1);
					}
				}
				
			}
			
		} while (toReturn);
	}
	
	public int getWeightBetweenVertices(Vertex pVertex1, Vertex pVertex2) {
		Edge e = getConnectedEdge(pVertex1, pVertex2);
		return e.getWeight();
	}

	public String toString() {
		if (vertices.size() == 0) {
			return new String("");
		}
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < totalVertices.size(); n++) {
			sb.append(totalVertices.get(n));
			sb.append(", ");
		}

		sb.deleteCharAt(sb.length() - 2);

		return sb.toString();

	}

	@Override
	public char[] HamiltonianCycle(char pVertexID) {
		startingVertex = findVertex(pVertexID);
		char[] arrayToReturn;
		if (!isConnected()) {
			System.out.println("Not connected");
			return new char[0];
		}

		hamiltonianCycle = null;
		hamiltonianCycleRecursion(null, startingVertex, vertices);
		firstIteration = true;
		if (hamiltonianCycle == null) {
			System.out.println("No hamiltonian cycle for vertex " + pVertexID);
			return new char[0];
		}
		arrayToReturn = new char[hamiltonianCycle.size()];
		for (int n = 0; n < hamiltonianCycle.size(); n++)
			arrayToReturn[n] = hamiltonianCycle.get(n).getID();

		return arrayToReturn;
	}

	private void hamiltonianCycleRecursion(List<Vertex> pPathTraveled,
			Vertex pCurrentVertex, List<Vertex> pVerticesRemaining) {
		List<Vertex> pathTraveled = new ArrayList<Vertex>();
		List<Vertex> connectedVertices = new ArrayList<Vertex>();
		List<Vertex> verticesRemaining = new ArrayList<Vertex>();
		Vertex currentVertex = pCurrentVertex;
		boolean toReturn = false;

		verticesRemaining.addAll(pVerticesRemaining);
		if (hamiltonianCycle != null)
			return;
		if (firstIteration)
			pathTraveled.add(currentVertex);
		if (!firstIteration)
			pathTraveled.addAll(pPathTraveled);
		connectedVertices.addAll(getAdjacentVertices(currentVertex));
		do {
			if (!firstIteration && verticesRemaining.size() == 0
					&& currentVertex == startingVertex) {
				if (hamiltonianCycle == null)
					hamiltonianCycle = pathTraveled;
				toReturn = false;
			} else {
				firstIteration = false;
				toReturn = false;
				for (int n = 0; n < connectedVertices.size(); n++) {
					Vertex v = connectedVertices.get(n);
					currentVertex = connectedVertices.get(n);
					
					if (verticesRemaining.contains(v)) {
						if (currentVertex == startingVertex && verticesRemaining.size() == 1) {
							verticesRemaining.remove(v);
							pathTraveled.add(v);
							toReturn = true;
							break;
						} else {
							verticesRemaining.remove(v);
							pathTraveled.add(v);
							hamiltonianCycleRecursion(pathTraveled, currentVertex, verticesRemaining);
							if (hamiltonianCycle == null) {
								verticesRemaining.add(v);
								pathTraveled.remove(pathTraveled.size() - 1);
							}
						}
					}
				}
			}
		} while (toReturn);
	}

	@Override
	public char[] HamiltonianPath(char pVertex1ID, char pVertex2ID) {
		Vertex startingVertex = findVertex(pVertex1ID);
		endingVertex = findVertex(pVertex2ID);
		char[] arrayToReturn;
		if (!isConnected()) {
			System.out.println("Not connected");
			return new char[0];
		}

		hamiltonianPath = null;
		hamiltonPathRecursion(null, startingVertex, vertices);
		firstIteration = true;
		if (hamiltonianPath == null) {
			System.out.println("No hamiltonian path between " + pVertex1ID
					+ " and " + pVertex2ID);
			return new char[0];
		}
		arrayToReturn = new char[hamiltonianPath.size()];
		for (int n = 0; n < hamiltonianPath.size(); n++) {
			arrayToReturn[n] = hamiltonianPath.get(n).getID();
		}
		return arrayToReturn;
	}

	private void hamiltonPathRecursion(List<Vertex> pPathVisited,
			Vertex pCurrentVertex, List<Vertex> pVerticesRemaining) {
		List<Vertex> pathTraveled = new ArrayList<Vertex>();
		List<Vertex> connectedVertices = new ArrayList<Vertex>();
		List<Vertex> verticesRemaining = new ArrayList<Vertex>();
		Vertex currentVertex = pCurrentVertex;
		boolean toReturn = false;

		verticesRemaining.addAll(pVerticesRemaining);
		if (hamiltonianPath != null)
			return;
		if (firstIteration) {
			pathTraveled.add(currentVertex);
			verticesRemaining.remove(currentVertex);
		}
		if (!firstIteration)
			pathTraveled.addAll(pPathVisited);
		connectedVertices.addAll(getAdjacentVertices(currentVertex));
		do {
			if (!firstIteration && verticesRemaining.size() == 0
					&& currentVertex == endingVertex) {
				if (hamiltonianPath == null)
					hamiltonianPath = pathTraveled;
				toReturn = false;
			} else {
				firstIteration = false;
				toReturn = false;

				for (int n = 0; n < connectedVertices.size(); n++) {
					Vertex v = connectedVertices.get(n);
					currentVertex = connectedVertices.get(n);

					if (verticesRemaining.contains(v)) {
						if (currentVertex == endingVertex
								&& verticesRemaining.size() == 1) {
							verticesRemaining.remove(v);
							pathTraveled.add(v);
							toReturn = true;
							break;
						} else {
							verticesRemaining.remove(v);
							pathTraveled.add(v);
							hamiltonPathRecursion(pathTraveled, currentVertex,
									verticesRemaining);
							if (hamiltonianPath == null) {
								verticesRemaining.add(v);
								pathTraveled.remove(v);
							}
						}

					}
				}
			}

		} while (toReturn);

		return;
	}

	@Override
	public char[] StrongEulerCycle(char pVertexID) {
		List<Edge> connectedEdges = new ArrayList<Edge>();
		List<Edge> edgesTraveled = new ArrayList<Edge>();
		List<Character> visitedVertices = new ArrayList<Character>();
		Vertex currentVertex = null;
		char[] arrayToReturn;

		firstIteration = true;
		eulerPath = null;

		for (Vertex v : vertices) {
			if (v.getID() == pVertexID) {
				currentVertex = v;
				break;
			}
		}
		if (currentVertex == null) {
			return new char[0];
		}

		if (hasStrongEulerCycle('A')) {
			visitedVertices = searchWithDepthFirst(currentVertex);
		} else {
			return new char[0];
		}
		firstIteration = true;
		// return the cycle starting at pVertexID
		arrayToReturn = new char[visitedVertices.size()];
		for (int n = 0; n < visitedVertices.size(); n++) {
			arrayToReturn[n] = visitedVertices.get(n);
		}
		return arrayToReturn;
	}

	@Override
	public boolean hasStrongEulerCycle(char pVertexID) {
		List<Edge> connectedEdges = new ArrayList<Edge>();
		int edgeCounter = 0;
		boolean vertexHasNoEdges = false;

		if (!isConnected()) {
			return false;
		}

		for (Vertex v : vertices) {
			connectedEdges = getAdjacentEdges(v);
			if (connectedEdges.size() == 0) {
				vertexHasNoEdges = true;
			}
			if (connectedEdges.size() % 2 == 0) {

			} else {
				edgeCounter++;
			}
		}
		if (edgeCounter == 0 && !vertexHasNoEdges) {
			return true;
		} else {
			return false;
		}
	}
}
