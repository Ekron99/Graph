package bp;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements IVertex {

	private char vertexID;
	private boolean hasVisited = false;
	private List<Edge> vertexEdges = new ArrayList<Edge>();

	public Vertex(char pID) {
		vertexID = pID;
		// increases the count so I can track it in isConnected()
		Graph.totalVertices.add(pID);
	}

	@Override
	public char getID() {
		return vertexID;
	}

	@Override
	public void setID(char pID) {
		vertexID = pID;
	}

	@Override
	public List<Edge> getEdges() {
		return vertexEdges;
	}

	@Override
	public void setEdges(List<Edge> edges) {
		vertexEdges = edges;
	}

	@Override
	public void addEdge(Edge pEdge) {
		vertexEdges.add(pEdge);
	}

	@Override
	public void removeEdge(int pIndex) {
		if (vertexEdges.contains(pIndex)) {
			vertexEdges.remove(pIndex);
		} else {
			System.out.println("Edge does not exist, cannot remove");
		}
	}

	public void setHasVisited(boolean pVisit) {
		hasVisited = pVisit;
	}

	public boolean getHasVisited() {
		return hasVisited;
	}
	
	public String toString() {
		return String.valueOf(vertexID);
	}

}
