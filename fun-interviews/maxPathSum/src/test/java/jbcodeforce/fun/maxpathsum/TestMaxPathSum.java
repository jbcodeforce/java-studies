package jbcodeforce.fun.maxpathsum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMaxPathSum {



    @Test 
    public void shouldBeMinValueForEmptryTree() {
        MaxPathSumbinaryTree tree = new MaxPathSumbinaryTree();
        Assertions.assertEquals(Integer.MIN_VALUE,tree.findMaxSum());
    }


    @Test 
    public void testOnNode() {
        MaxPathSumbinaryTree tree = new MaxPathSumbinaryTree();
        tree.root = new Node(1);
        Assertions.assertEquals(1,tree.findMaxSum());
    }


    @Test 
    public void testTwoNodes() {
        MaxPathSumbinaryTree tree = new MaxPathSumbinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(3);
        Assertions.assertEquals(4,tree.findMaxSum());
    }

    @Test 
    public void shouldGetTheSumOfThreeNodes() {
        MaxPathSumbinaryTree tree = new MaxPathSumbinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(3);
        tree.root.right = new Node(4);
        Assertions.assertEquals(8,tree.findMaxSum());
    }

    @Test 
    public void testThreeNodes() {
        MaxPathSumbinaryTree tree = new MaxPathSumbinaryTree();
        tree.root = new Node(10);
        tree.root.left = new Node(2);
        tree.root.right = new Node(-25);
        tree.root.left.left = new Node(20);
        tree.root.left.right = new Node(1);
        tree.root.right.left = new Node(3);
        tree.root.right.right = new Node(4);
        Assertions.assertEquals(32,tree.findMaxSum());
    }
}
