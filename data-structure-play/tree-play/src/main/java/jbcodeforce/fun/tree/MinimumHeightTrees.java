package jbcodeforce.fun.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        List<Integer> solution = new ArrayList<>();
        MinimumHeightTrees mhtBuilder = new MinimumHeightTrees();
        int minHeight = 100;
        HashMap<Integer,Node> nodes = mhtBuilder.buildTree(n,edges);
        for(Node root: nodes.values()) {

        }
        return solution;
    }
    
    public HashMap<Integer,Node> buildTree(int n,int[][] edges) {
        HashMap<Integer,Node> nodes = new HashMap<Integer,Node>();
        for (int i = 0; i<n;i++) {
            Node node = new Node(i);
            nodes.put(node.value, node);
        }
       
        for (int i=0; i< edges.length;i++) {
            Integer keyA = Integer.valueOf(edges[i][0]);
            Integer keyB = Integer.valueOf(edges[i][1]);
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
        public HashMap<Integer,Node> vertices;

        public Node(int v){
            this.value = v;
            vertices = new HashMap<Integer,Node>();
        }

        public void addEdgeTo(Node b) {
            if (! this.vertices.containsKey(b.value)) {
                this.vertices.put(b.value,b);
            }
            if (! b.vertices.containsKey(this.value)) {
                b.vertices.put(this.value, this);
            }
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
