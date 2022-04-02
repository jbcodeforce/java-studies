package jbcodeforce.fun.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    Node left;
    Node right;
    
    public Node(String name) {
        this.name = name;
    }

    public static void dfs(Node currentNode, List<Node> result) {
        if (currentNode != null && ! result.contains(currentNode)) {
            result.add(currentNode);
            Node.dfs(currentNode.left, result);
            Node.dfs(currentNode.right, result);
        }
    }

    /**
     * Append current node to result, then traverse left subtree before right subtree
     * @param root
     * @param result
     */
    public static void traversePreOrder(Node currentNode, List<Node> result) {
        if (result == null) {
            result = new ArrayList<Node>();
        }
        if (currentNode != null) {
            result.add(currentNode);
            Node.traversePreOrder(currentNode.left, result);
            Node.traversePreOrder(currentNode.right, result);
        }
    }

    public static void traverseInOrder(Node currentNode, List<Node> result) {
        if (result == null) {
            result = new ArrayList<Node>();
        }
        if (currentNode != null) {
            Node.traverseInOrder(currentNode.left, result);
            result.add(currentNode);
            Node.traverseInOrder(currentNode.right, result);
        }
    }
   

    public String toString() {
        return name;
    }

    public static void traversePostOrder(Node currentNode, List<Node> result) {
        if (result == null) {
            result = new ArrayList<Node>();
        }
        if (currentNode != null) {
            Node.traverseInOrder(currentNode.left, result);
            Node.traverseInOrder(currentNode.right, result);
            result.add(currentNode);
        }
    }
}
