package bp;

public class Edge implements IEdge {

	private Vertex vertexOne;
	private Vertex vertexTwo;
	private int weight = 0;
	boolean isLoop = false;
	boolean traveled = false;
	
	public Edge(Vertex v1, Vertex v2, int pWeight) {
		setVertex1(v1);
		setVertex2(v2);
		weight = pWeight;
		if (v1 == v2) {
			setLoop(true);
		}
	}
	
	public boolean contains(Vertex pVertex) {
		if (vertexOne == pVertex || vertexTwo == pVertex)
			return true;
		else
			return false;
	}
	
	public void setLoop(boolean pValue) {
		isLoop = pValue;
	}
	
	public boolean getIsLoop() {
		return isLoop;
	}
	
	public boolean beenTraveled() {
		return traveled;
	}
	
	public void setWeight(int pWeight) {
		weight = pWeight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	@Override
	public Vertex getVertex1() {
		return vertexOne;
	}

	@Override
	public void setVertex1(Vertex vertex1) {
		vertexOne = vertex1;
	}

	@Override
	public Vertex getVertex2() {
		return vertexTwo;
	}

	@Override
	public void setVertex2(Vertex vertex2) {
		vertexTwo = vertex2;
	}
	
	public String toString() {
		return vertexOne.getID() + " - " + vertexTwo.getID();
	}

	public void setTraveled(boolean pValue) {
		traveled = pValue;
		
	}

}
