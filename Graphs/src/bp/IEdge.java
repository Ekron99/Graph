package bp;

/**
 * Defines the properties and methods associated with an edge in a graph.
 * 
 * @author cberkstresser
 * 
 */
interface IEdge {

	/**
	 * 
	 * @return Returns the first vertex of the edge.
	 */
	Vertex getVertex1();

	/**
	 * Sets the first vertex of the edge.
	 * 
	 * @param vertex1
	 *            The first vertex of the edge to set.
	 */
	void setVertex1(Vertex vertex1);

	/**
	 * 
	 * @return Returns the second vertex of the edge.
	 */
	Vertex getVertex2();

	/**
	 * Sets the second vertex of the edge.
	 * 
	 * @param vertex2
	 *            The second vertex of the edge to set.
	 */
	void setVertex2(Vertex vertex2);

}