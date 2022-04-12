package jbcodeforce.fun.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;

/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, 
 * any connected graph without simple cycles is a tree.
Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there 
is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select 
a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called 
minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class MinimumHeightTrees {
    
    /**
     * Return the list of MHT root labels.
     * Compute each node height using DFS, ad then take the minimum 
     * @param n number of nodes
     * @param edges
     * @return
     */
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        if (n < 2) {
            ArrayList<Integer> solution = new ArrayList<>();
            for (int i = 0; i < n; i++)
                solution.add(i);
            return solution;
        }

        List<Integer> solution = new ArrayList<>();
        MinimumHeightTrees mhtBuilder = new MinimumHeightTrees();
        int minHeight = 100;
        HashMap<Integer,Node> nodes = mhtBuilder.buildTree(n,edges);
        ArrayList<Node> visited = new ArrayList<>();
        for(Node root: nodes.values()) {
          
            int d = computeDistanceToLeaf(root,visited);
            if (d < minHeight) {
                minHeight = d;
                root.height = d;
            }
        }
        return solution;
    }
    
    private static int computeDistanceToLeaf(Node root, List<Node> visited) {
        visited.add(root);
        int max = 0;
        for (Node child : root.vertices) {
            if (! visited.contains(child)) {
                int currentdistance = computeDistanceToLeaf(child,visited) + 1;
                max = Math.max(max, currentdistance);
                child.height = max;
            }
        }
        return max;
    }

    public HashMap<Integer,Node> buildTree(int n,int[][] edges) {
        HashMap<Integer,Node> nodes = new HashMap<Integer,Node>();
        for (int i = 0; i<n;i++) {
            Node node = new Node(i);
            nodes.put(node.value, node);
        }
       
        for (int[] edge : edges ) {
            Integer keyA = edge[0], keyB = edge[1];
            Node a = nodes.get(keyA);
            Node b = nodes.get(keyB);
            a.addEdgeTo(b); // add b to a too
        }
        return nodes;
    }

    public static void main(String[] args) {
     
        int[][] edges = {{1,0},{1,2},{1,3}};
        List<Integer> sol = findMinHeightTrees(4,edges);
        Assertions.assertEquals(1,sol.get(0));
    }


    public class Node {
        public int value;
        public int height;
        public Set<Node> vertices;

        public Node(int v){
            this.value = v;
            vertices = new HashSet<Node>();
        }

        public void addEdgeTo(Node b) {
            this.vertices.add(b);
            b.vertices.add(this);
        }

        /**
         * Compute the heigh to reach a leaf
         * @param current
         * @param h
         */
        public void dfs(Node current, int h){
            
        }
    }
}
