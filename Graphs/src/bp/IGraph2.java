package bp;

public interface IGraph2 extends IGraph {
	/**
	 * Depth First Search of the given graph.
	 * 
	 * @return Returns a character array of vertex ids representing the path
	 *         taken during the search.
	 */
	char[] DepthFirstSearch();

	/**
	 * Breadth First Search of the given graph.
	 * 
	 * @return Returns a character array of vertex ids representing the path
	 *         taken during the search.
	 */
	char[] BreadthFirstSearch();

	/**
	 * A Hamiltonian cycle visits each vertex in the graph once except the
	 * starting vertex, which is visited twice.
	 * 
	 * @param pVertexID
	 *            The vertex id which to start/end the cycle.
	 * @return Returns a character array of the cycle if it exists. Otherwise
	 *         returns an array of size zero.
	 */
	char[] HamiltonianCycle(char pVertexID);

	/**
	 * A Hamiltonian path visits each vertex in the graph once.
	 * 
	 * @param pVertex1ID
	 *            The vertex id which to start the path
	 * @param pVertex2ID
	 *            The vertex id which to end the path
	 * @return Returns a character array of the path if it exists. Otherwise
	 *         returns an array of size zero.
	 */
	char[] HamiltonianPath(char pVertex1ID, char pVertex2ID);

	/**
	 * An Euler cycle traverses each edge in the graph once. In the case of this
	 * function, we denote a strong Euler cycle to require a graph be connected.
	 * 
	 * @param pVertexID
	 *            The vertex id which to start/end the cycle.
	 * @return Returns a character array of the cycle if it exists. Otherwise
	 *         returns an array of size zero.
	 */
	char[] StrongEulerCycle(char pVertexID);

	/**
	 * An Euler cycle traverses each edge in the graph once. In the case of this
	 * function, we denote a strong Euler cycle to require a graph be connected.
	 * 
	 * @param pVertexID
	 *            The vertex id which to start/end the cycle.
	 * @return Returns true if the graph is connected and an Euler Cycle exists
	 *         starting and ending with pVertexID.
	 */
	boolean hasStrongEulerCycle(char pVertexID);

	/**
	 * Is the graph complete.
	 * 
	 * @return Returns true if the graph is complete. To show a graph is
	 *         complete, choose any two vertices--an edge will exist between
	 *         them.
	 */
	boolean isComplete();
}
