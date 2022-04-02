package jbcodeforce.fun.maxpathsum;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a Binary Tree, find the maximum sum path from a leaf to root.
 * Return the sum but also the path.
 */
public class TestLeafToRootMaxSum {
    // binary tree

    public int maxSumLeafToRoot(Node currentNode, List<Node> path) {
        if (currentNode != null) {
            List<Node> leftPath = new ArrayList<Node>();
            int leftSum = maxSumLeafToRoot(currentNode.left, leftPath) + currentNode.value;
            List<Node> rightPath = new ArrayList<Node>();
            int rightSum = maxSumLeafToRoot(currentNode.right, rightPath) + currentNode.value;
            if (leftSum > rightSum) {
                path= leftPath;
                path.add(currentNode);
                return leftSum;
            } else {
                path = rightPath;
                path.add(currentNode);
                return rightSum;
            }
           
        }
        // leaf
        return 0;
    }


    @Test
    public void shouldBe10ForThisSimpleTree(){
        Node root = new Node(7);
        root.left = new Node(3);
        root.right = new Node(2);
        List<Node> path = new ArrayList<Node>();
        Assertions.assertEquals(10,maxSumLeafToRoot(root,path));
        System.out.println(path);
    }

    @Test
    public void shouldBe20ForThisSimpleTree(){
        Node root = new Node(10);
        root.left = new Node(7);
        root.right = new Node(-5);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(4);
        List<Node> path = new ArrayList<Node>();
        Assertions.assertEquals(21,maxSumLeafToRoot(root,path));
        // 4,7,10
        System.out.println(path);
    }
}
