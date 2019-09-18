package jbcodeforce.graph;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GraphTest 
{
    /**
     * Validate graph creation- edges are bidirectional
     * a -> b -> c -> d
     * a -> d
     * b -> e
     */
    @Test
    public void shouldHaveExpectedNumberOfVertices()
    {
    	Graph simpleGraph = new Graph();
    	simpleGraph.addVertex("a");
    	simpleGraph.addVertex("b");
    	simpleGraph.addVertex("c");
    	simpleGraph.addVertex("d");
    	simpleGraph.addVertex("e");
        assertTrue( simpleGraph.numberOfVertices() == 5 );
        simpleGraph.addEdge("a","b");
        simpleGraph.addEdge("a","d");
        simpleGraph.addEdge("b","c");
        simpleGraph.addEdge("c","d");
        simpleGraph.addEdge("b","e");
        assertTrue( simpleGraph.numberOfEdges() == 10 );
    }
    
    @Test 
    public void shouldTraveseGraphDepthFirst() {
    	Graph simpleGraph = new Graph();
    	simpleGraph.addVertex("a");
    	simpleGraph.addVertex("b");
    	simpleGraph.addVertex("c");
    	simpleGraph.addVertex("d");
    	simpleGraph.addVertex("e");
    	simpleGraph.addEdge("a","b");
        simpleGraph.addEdge("a","d");
        simpleGraph.addEdge("b","c");
        simpleGraph.addEdge("c","d");
        simpleGraph.addEdge("b","e");
    	Assert.assertEquals("[a, d, c, b, e]",simpleGraph.depthFirstTraversal(simpleGraph, "a").toString());
    }
    
    @Test 
    public void shouldTraveseGraphBreadthFirst() {
    	Graph simpleGraph = new Graph();
    	simpleGraph.addVertex("a");
    	simpleGraph.addVertex("b");
    	simpleGraph.addVertex("c");
    	simpleGraph.addVertex("d");
    	simpleGraph.addVertex("e");
    	simpleGraph.addEdge("a","b");
        simpleGraph.addEdge("a","d");
        simpleGraph.addEdge("b","c");
        simpleGraph.addEdge("c","d");
        simpleGraph.addEdge("b","e");
    	Assert.assertEquals("[a, b, d, c, e]",simpleGraph.breadthFirstTraversal(simpleGraph, "a").toString());
    }
}
