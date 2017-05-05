import java.util.Comparator;

/**
 * 
 * @author Meggie Cook
 *
 */
class PQWrapper implements Comparator<PQWrapper>{

	GraphNode<Location, Path> node;
	boolean visited;
	Double weight;
	PQWrapper predecessors;
	
	/**
	 * Constructor of the Wrapper class.
	 * @param node
	 */
	PQWrapper(GraphNode<Location, Path> node) {
		// TODO Auto-generated constructor stub
		this.node = node;
		visited = false;
		weight = Double.POSITIVE_INFINITY;
		predecessors = null;
	}
	
	/**
	 * Set visited so its true
	 */
	void setVisitedTrue()
	{
		visited = true;
	}
	
	/**
	 * Set weight to any value
	 * @param num is the value for the new weight
	 */
	void setWeightTo(Double num)
	{
		weight = num;
	}
	
	/**
	 * Set predecessor to this PQWrapper
	 * @param pred is the predecessor for this object
	 */
	void setPredecessor(PQWrapper pred)
	{
		predecessors = pred;
	}
	
	/**
	 * Getter of the GraphNode for this Wrapper class
	 * @return
	 */
	GraphNode<Location, Path> getNode()
	{
		return node;
	}
	
	/**
	 * Compare method used for the Priority Queue.
	 */
	public int compare(PQWrapper o1, PQWrapper o2) {
		// TODO Auto-generated method stub
		if(o1.weight > o2.weight)
			return 1;
		else if (o1.weight < o2.weight)
			return -1;
		return 0;
	}
}
