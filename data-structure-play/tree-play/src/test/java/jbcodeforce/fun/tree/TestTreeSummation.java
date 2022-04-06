package jbcodeforce.fun.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTreeSummation {

    /**
     * Given a binary tree, task is to find subtree with maximum sum in tree.
     * Questions:
     * - is the tree sorted? No
     * - Is there any negative valued?
     * If all positive the max sum is the sum of all elements in the tree. So using
     * a post order traversal will compute the max sum
     * The value of subtree rooted at current node is equal to sum of current node
     * value, left node subtree sum
     * and right node subtree sum. We can add accumulatedSum in the IntNode
     * definition
     * 
     */


    @Test
    public void maxSumInRoot() {
        IntNode r = new IntNode(20);
        BinaryTree tree = new BinaryTree(r);
        IntNode maxTree = new IntNode(Integer.MIN_VALUE);
        Assertions.assertEquals(20, tree.getMaxSumSubtree(r, maxTree));
        Assertions.assertEquals(20, maxTree.accumulatedSum);
    }

    @Test
    public void maxSumIn3Nodes() {
        int[] inA = { 5, 8, 3 };
        BinaryTree tree = new BinaryTree();
        tree.buildFromArray(inA);
        IntNode maxTree = new IntNode(Integer.MIN_VALUE);
        Assertions.assertEquals(16, tree.getMaxSumSubtree(tree.getRoot(), maxTree));
        Assertions.assertEquals(16, maxTree.accumulatedSum);
    }

    @Test
    public void maxSumIn10Nodes() {
        int[] inA = { 5, 8, 3, 2, 10, 1, 6, 7, 8, 11 };
        BinaryTree tree = new BinaryTree();
        tree.buildFromArray(inA);
        IntNode maxTree = new IntNode(Integer.MIN_VALUE);
        Assertions.assertEquals(61, tree.getMaxSumSubtree(tree.getRoot(), maxTree));
        Assertions.assertEquals(61, maxTree.accumulatedSum);
    }

    @Test
    public void maxLeftSubTreeNegative() {
        int[] inA = { 5, -4, -6, -10, 10, 11, 12 };
        BinaryTree tree = new BinaryTree();
        tree.buildFromArray(inA);
        IntNode maxTree = new IntNode(Integer.MIN_VALUE);
        Assertions.assertEquals(18, tree.getMaxSumSubtree(tree.getRoot(), maxTree));
        Assertions.assertEquals(10, maxTree.value);
        Assertions.assertEquals(33, maxTree.accumulatedSum);
    }

    @Test
    public void printRoot(){
        BinaryTree tree = new BinaryTree(new IntNode(21));
        tree.displayRooToLeafPaths();
        int[] inA = { 5, -4, -6, -10, 10, 11, 12 };
        tree = new BinaryTree();
        tree.buildFromArray(inA);
        tree.displayRooToLeafPaths();
    }

    @Test
    public void testPrintHorizontaly(){
        int[] inA = { 5, 8, 3, 2, 10, 1, 6, 7, 8, 11 };
        BinaryTree tree = new BinaryTree();
        tree.buildFromArray(inA);
        tree.printTree(System.out);
    }
}
