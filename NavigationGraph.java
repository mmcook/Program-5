import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class NavigationGraph implements GraphADT<Location, Path> {

	//TODO: Implement all methods of GraphADT
	
	String[] edgePropertyNames;
	//PriorityQueue<Location> vertexQueue;
	List<Location> listLocation;
	
	public NavigationGraph(String[] edgePropertyNames) {
		this.edgePropertyNames = edgePropertyNames;
		//vertexQueue = new PriorityQueue<Location>();
		listLocation = new ArrayList<Location>();
	}

	
	/**
	 * Returns a Location object given its name
	 * 
	 * @param name
	 *            name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) {
		
		for(int i = 0; i < listLocation.size(); i++)
			if(listLocation.get(i).getName().equals(name))
				return listLocation.get(i);
		return null; //TODO: implement correctly. 
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
		List<Path> list = new ArrayList<Path>();
		
		
		
		return list;
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
		return null;
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
		return null;
	}

}
