package bp;

import java.util.List;

/**
 * Defines the properties and methods of a vertex in a graph.
 * 
 * @author cberkstresser
 * 
 */
interface IVertex {
	/**
	 * 
	 * @return Returns the ID of the vertex.
	 */
	char getID();

	/**
	 * Sets the ID of the vertex.
	 * 
	 * @param pID
	 *            The ID of the vertex to set.
	 */
	void setID(char pID);

	/**
	 * 
	 * @return Returns a list of the edges connected to this vertex.
	 */
	List<Edge> getEdges();

	/**
	 * Sets the edges connected to this vertex.
	 * 
	 * @param edges
	 *            The list of edges to set.
	 * @see addEdge for a more granular approach. Edges should generally be set
	 *      through that function
	 */
	void setEdges(List<Edge> edges);

	/**
	 * Adds an edge to this vertex.
	 * 
	 * @param pEdge
	 *            The edge to add to this vertex.
	 */
	void addEdge(Edge pEdge);

	/**
	 * Removes an edge from the vertex.
	 * 
	 * @param pIndex
	 *            The id of the edge to remove.
	 */
	void removeEdge(int pIndex);

}