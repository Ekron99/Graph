package bp;

import java.util.List;

/**
 * The properties and methods associated with a graph.
 */
interface IGraph {
	Vertex getVertexByID(char pID);
	
	/**
	 * 
	 * @return The edges contained in the graph.
	 */
	List<Edge> getEdges();

	/**
	 * Sets the edges affiliated with this graph.
	 * 
	 * @param pEdges
	 *            The list of edges to add.
	 * @see addEdge for a more granular approach. Edges should generally be set
	 *      through that function
	 */
	void setEdges(List<Edge> pEdges);

	/**
	 * 
	 * @return The list of vertices in this graph.
	 */
	List<Vertex> getVertices();

	/**
	 * Sets the vertices in this graph.
	 * 
	 * @param pVertices
	 *            The list of edges to add.
	 * @see addVertex for a more granular approach. Vertices should generally be
	 *      set through that function
	 */
	void setVertices(List<Vertex> pVertices);

	/**
	 * Adds an edge to the graph.
	 * 
	 * @param pEdge
	 *            The edge to add to the graph.
	 */
	void addEdge(Edge pEdge);

	/**
	 * Removes an edge from the graph.
	 * 
	 * @param pIndex
	 *            The index of the edge to remove from the graph.
	 */
	void removeEdge(int pIndex);

	/**
	 * Adds a vertex to the graph.
	 * 
	 * @param pVertex
	 *            The vertex to add to the graph.
	 */
	void addVertex(Vertex pVertex);

	/**
	 * Removes a vertex from the graph.
	 * 
	 * @param pIndex
	 *            The index of the vertex to remove from the graph.
	 */
	void removeVertex(int pIndex);

	/**
	 * 
	 * @return True if the graph is a connected graph.
	 */
	boolean isConnected();

	/**
	 * 
	 * @param pVertex1ID
	 *            The id of the first vertex.
	 * @param pVertex2ID
	 *            The id of the second vertex.
	 * @return True if the two vertices given by their ID are adjacent.
	 */
	boolean isAdjacent(char pVertex1ID, char pVertex2ID);

	/**
	 * Finds the shortest path between vertices. In the case of duplicate short
	 * paths, will pick one at random.
	 * 
	 * @param pVertex1ID
	 *            The id of the first vertex.
	 * @param pVertex2ID
	 *            The id of the second vertex.
	 * @return A character array of the ids of the vertices in the shortest
	 *         path.
	 */
	default char[] getShortestPath(char pVertex1ID, char pVertex2ID) {
		throw new UnsupportedOperationException("Not implemented yet");
	};

	/**
	 * Show a list of the ids of all adjacent vertices.
	 * 
	 * @param pVertexID
	 *            The vertex id of which to show all adjacent vertices.
	 * @return A character array of the ids of the vertices that are adjacent.
	 */
	char[] getAdjacentVertices(char pVertexID);

}