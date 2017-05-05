import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class NavigationGraph implements GraphADT<Location, Path> {

	//TODO: Implement all methods of GraphADT
	
	String[] edgePropertyNames;

	List<Location> listLocation;
	
	List<Path> listPath;
	
	private List<GraphNode<Location, Path>> listNodes;
	
	private int numVertex;
	
	public NavigationGraph(String[] edgePropertyNames) {
		this.edgePropertyNames = edgePropertyNames;
		
		listLocation = new ArrayList<Location>();
		listPath = new ArrayList<Path>();
		
		listNodes = new ArrayList<GraphNode<Location, Path>>();
		numVertex = 0;
	}

	
	/**
	 * Returns a Location object given its name
	 * 
	 * @param name
	 *            name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) {
		
		for(int i = 0; i < listNodes.size(); i++)
			if(listNodes.get(i).getVertexData().getName().equals(name))
				return listNodes.get(i).getVertexData();
		return null;  
	}

	/**
	 * Adds a vertex to the Graph
	 * 
	 * @param vertex
	 *            vertex to be added
	 */
	public void addVertex(Location vertex)
	{
		listLocation.add(vertex);
		
		GraphNode<Location, Path> node = new GraphNode<Location, Path>(vertex, numVertex);
		listNodes.add(node);
		numVertex++;
		
	}
	

	/**
	 * Creates a directed edge from src to dest
	 * 
	 * @param src
	 *            source vertex from where the edge is outgoing
	 * @param dest
	 *            destination vertex where the edge is incoming
	 * @param edge
	 *            edge between src and dest
	 */
	public void addEdge(Location src, Location dest, Path edge)
	{
		
		for(int i = 0; i < listNodes.size(); i++)
		{
			if(listNodes.get(i).getVertexData().equals(src))
				listNodes.get(i).addOutEdge(edge);
		}
		
		listPath.add(edge);
	}

	/**
	 * Getter method for the vertices
	 * 
	 * @return List of vertices of type V
	 */
	public List<Location> getVertices()
	{	
		return listLocation;
	}

	/**
	 * Returns edge if there is one from src to dest vertex else null
	 * 
	 * @param src
	 *            Source vertex
	 * @param dest
	 *            Destination vertex
	 * @return Edge of type E from src to dest
	 */
	public Path getEdgeIfExists(Location src, Location dest)
	{
		for(int i = 0; i < listPath.size(); i++)
			if(listPath.get(i).getSource().equals(src) &&
					listPath.get(i).getDestination().equals(dest))
				return listPath.get(i);
		
		return null;
	}

	/**
	 * Returns the outgoing edges from a vertex
	 * 
	 * @param src
	 *            Source vertex for which the outgoing edges need to be obtained
	 * @return List of edges of type E
	 */
	public List<Path> getOutEdges(Location src)
	{	
		GraphNode<Location, Path> node = null;
		
		for(int i = 0; i < listNodes.size(); i++)
		{
			if(src.equals(listNodes.get(i).getVertexData()))
				node = listNodes.get(i);
		}
		
		return node.getOutEdges();
	}

	/**
	 * Returns neighbors of a vertex
	 * 
	 * @param vertex
	 *            vertex for which the neighbors are required
	 * @return List of vertices(neighbors) of type V
	 */
	public List<Location> getNeighbors(Location vertex)
	{
		List<Location> list = new ArrayList<Location>();
		
		GraphNode<Location, Path> node = null;
		
		for(int i = 0; i < listNodes.size(); i++)
		{
			if(vertex.equals(listNodes.get(i).getVertexData()))
				node = listNodes.get(i);
		}
		
		for(int i = 0; i < node.getOutEdges().size(); i++)
			list.add(node.getOutEdges().get(i).getDestination());
		
		return list;
	}

	/**
	 * Calculate the shortest route from src to dest vertex using
	 * edgePropertyName
	 * 
	 * @param src
	 *            Source vertex from which the shortest route is desired
	 * @param dest
	 *            Destination vertex to which the shortest route is desired
	 * @param edgePropertyName
	 *            edge property by which shortest route has to be calculated
	 * @return List of edges that denote the shortest route by edgePropertyName
	 */
	public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName)
	{
		boolean done = false;
		
		//Set which edge property to use depending on the edgeProperteName parameter
		int c = 0;
		if(edgePropertyName.equals(edgePropertyNames[1]))
			c = 1;
		
		List<Path> shortestRoute= new ArrayList<Path>();
		//Create list for Wrapper class objects
		List<PQWrapper> pqentry = new ArrayList<PQWrapper>();
		
		//Fill list for Wrapper class objects
		for(int i = 0; i < listNodes.size(); i++)
		{
			pqentry.add(new PQWrapper(listNodes.get(i)));
		}
		
		//Find vertex for the destination
		GraphNode<Location, Path> destNode = null;
		for(int i = 0; i < listNodes.size(); i++)
		{
			if(listNodes.get(i).getVertexData().equals(dest))
			{
				destNode = listNodes.get(i);
				break;
			}
		}
		
		//Set curr to the starting vertex
		GraphNode<Location, Path> currNode = null;
		PQWrapper currWrapper = null;
		for(int i = 0; i < listNodes.size(); i++)
		{
			if(listNodes.get(i).getVertexData().equals(src))
			{
				currNode = listNodes.get(i);
				currWrapper = pqentry.get(currNode.getId());
				//Set weight for starting vertex to 0
				currWrapper.setWeightTo((double)0);
				break;
			}
		}
		
		//Add starting vertex to the priority queue
		PriorityQueue<PQWrapper> pq = new PriorityQueue<PQWrapper>();
		pq.add(currWrapper);
		
		while(!done)
		{
			//Remove the vertex with the smallest weight 
			currWrapper = pq.remove();
			//Set visited as true
			currWrapper.setVisitedTrue();
			
			//Traverse through the outer edges of vertex
			for(int i = 0; i < currWrapper.getNode().getOutEdges().size(); i++)
			{
				//Find one of the successors in list of nodes in list
				Location succLoc = currWrapper.getNode().getOutEdges().get(i).getDestination();
				GraphNode<Location, Path> succNode = null;
				for(int j = 0; j < listNodes.size(); j++)
				{
					if(listNodes.get(j).getVertexData().equals(succLoc))
						succNode = listNodes.get(j);
				}
				PQWrapper succ = pqentry.get(succNode.getId());
				
				//If weight of successor is more than 0, proceed
				if(succ.weight > 0)
				{
					succ.setWeightTo(currWrapper.weight + 
							currWrapper.getNode().getOutEdges().get(i).getProperties().get(c));
					succ.setPredecessor(currWrapper);
					//Only add successor if its not in priority queue
					if(!pq.contains(succ))
						pq.add(succ);
				}
			}
			
			//Find edge between curr and the node with the smallest weight, add to list
			Path path = getEdgeIfExists(currWrapper.getNode().getVertexData(), 
											pq.peek().getNode().getVertexData());
			shortestRoute.add(path);
			
			//End loop if queue is empty or the queue read in the destination
			if(pq.contains(destNode) || pq.isEmpty())
				done = true;
		}
		
		
		return shortestRoute;
	}

	/**
	 * Getter method for edge property names
	 * 
	 * @return array of String that denotes the edge property names
	 */
	public String[] getEdgePropertyNames()
	{
		return edgePropertyNames;
	}

	/**
	 * Return a string representation of the graph
	 * 
	 * @return String representation of the graph
	 */
	public String toString()
	{
		String string = "";
		
		for(int i = 0; i < listNodes.size(); i++)
		{
			string += listNodes.get(i).getVertexData().toString();
			for(int j = 0; j < listNodes.get(i).getOutEdges().size(); j++)
				string += listNodes.get(i).getOutEdges().get(j).toString() + "\n";
			
			string += "\n";
		}
		
		return string;
	}

}
