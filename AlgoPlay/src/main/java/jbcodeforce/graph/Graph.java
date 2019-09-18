package jbcodeforce.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
	// use adjacent list
	Map<Vertex, ArrayList<Vertex>> adjacentVertices = new HashMap<Vertex, ArrayList<Vertex>>();
	
	
	public void addVertex(String label) {
		adjacentVertices.putIfAbsent(new Vertex(label), new ArrayList<Vertex>());
	}
	
	void removeVertex(String label) {
	    Vertex v = new Vertex(label);
	    adjacentVertices.values().stream().forEach(e -> e.remove(v));
	    adjacentVertices.remove(new Vertex(label));
	}

	public int numberOfVertices() {
		return adjacentVertices.size();
	}
	
	/**
	 * edges are bidirectional
	 * @param sourceLabel
	 * @param destinationLabel
	 */
	public void addEdge(String sourceLabel, String destinationLabel) {
		Vertex sourceVertex = new Vertex(sourceLabel);
		Vertex destinationVertex = new Vertex(destinationLabel);
		adjacentVertices.get(sourceVertex).add(destinationVertex);
		adjacentVertices.get(destinationVertex).add(sourceVertex);
	}

	public List<Vertex> getAdjacentVertices(Vertex v) {
		return adjacentVertices.get(v);
	}
	
	public List<Vertex> getAdjacentVertices(String label) {
		return adjacentVertices.get(new Vertex(label));
	}
	
	public long numberOfEdges() {
		long count = 0;
		for (List<Vertex> edges : adjacentVertices.values())
			count += edges.size();
		return count;
	}
	
	/**
	 * Traverse each node of the graph by doing depth first.
	 * A depth-first traversal starts at an arbitrary root vertex 
	 * and explores vertices as deeper as possible along each branch
	 * before exploring vertices at the same level.
	 * The approach is to use a stack of elements to traverse, and if not 
	 * already visited take its children and add them to the stack
	 * @param graph
	 * @param root node from which to traverse the graph
	 */
	public Set<String> depthFirstTraversal(Graph graph, String root) {
		Set<String> visitedNodes = new LinkedHashSet<String>();
		Stack<String> nodesToVisit = new Stack<String>();
		nodesToVisit.push(root);
		while (! nodesToVisit.isEmpty()) {
			String nodeToTraverse = nodesToVisit.pop();
			if (!visitedNodes.contains(nodeToTraverse)) {
				visitedNodes.add(nodeToTraverse);
				for (Vertex child: graph.getAdjacentVertices(nodeToTraverse)) {
					System.out.println(child.getLabel());
					nodesToVisit.push(child.getLabel());
				}
			}
		}
		return visitedNodes;
	}
	
	public Set<String> breadthFirstTraversal(Graph graph, String root) {
		Set<String> visitedNodes = new LinkedHashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.add(root);
		visitedNodes.add(root);
		while (! queue.isEmpty()) {
			String nodeToTraverse = queue.poll();
			for (Vertex child: graph.getAdjacentVertices(nodeToTraverse)) {
				System.out.println(child.getLabel());
				if (!visitedNodes.contains(child.getLabel())) {
					visitedNodes.add(child.getLabel());
					queue.add(child.getLabel());
				}
				
			}
		}
		return visitedNodes;
	}
}
