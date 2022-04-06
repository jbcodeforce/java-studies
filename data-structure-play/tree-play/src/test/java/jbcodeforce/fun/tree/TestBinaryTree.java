package jbcodeforce.fun.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBinaryTree {
    
    @Test
    public void shouldHaveOneRootNode(){
        BinaryTree tree = new BinaryTree();
        IntNode root = new IntNode(21);
        tree.insert(root);
        Assertions.assertNotNull(tree.getRoot());
        Assertions.assertEquals(21,tree.getRoot().value);
    }

    @Test
    public void shouldHaveOneRighOneLeftNodes(){
        BinaryTree tree = new BinaryTree();
        IntNode root = new IntNode(21);
        tree.insert(root);
        tree.insert(new IntNode(17));
        tree.insert(new IntNode(27));
        Assertions.assertNotNull(tree.getRoot());
        Assertions.assertEquals(21,tree.getRoot().value);
        Assertions.assertNotNull(tree.getRoot().left);
        Assertions.assertNotNull(tree.getRoot().right);
        Assertions.assertEquals(17,tree.getRoot().left.value);
        Assertions.assertEquals(27,tree.getRoot().right.value);
    }

    @Test
    public void shouldHaveSortedValueGoesOneBranch(){
        BinaryTree tree = new BinaryTree();
        IntNode root = new IntNode(21);
        tree.insert(root);
        tree.insert(new IntNode(19));
        tree.insert(new IntNode(17));
        tree.insert(new IntNode(15));
        tree.insert(new IntNode(13));
        Assertions.assertEquals(21,tree.getRoot().value);
        Assertions.assertNotNull(tree.getRoot().left);
        Assertions.assertNull(tree.getRoot().right);
        Assertions.assertEquals(19,tree.getRoot().left.value);
        Assertions.assertEquals(17,tree.getRoot().left.left.value);
        Assertions.assertEquals(15,tree.getRoot().left.left.left.value);
        Assertions.assertEquals(13,tree.getRoot().left.left.left.left.value);
        Assertions.assertEquals(13,tree.minimum().value);
        Assertions.assertEquals(21,tree.maximum().value);
    }

    @Test
    public void shouldHaveBalancedTree(){
        int[] inA = {21,18,13,19,27,22,29};
        BinaryTree tree = new BinaryTree();
        tree.buildFromArray(inA);
        Assertions.assertEquals(21,tree.getRoot().value);
        Assertions.assertEquals(18,tree.getRoot().left.value);
        Assertions.assertEquals(27,tree.getRoot().right.value);
        Assertions.assertEquals(13,tree.getRoot().left.left.value);
        Assertions.assertEquals(19,tree.getRoot().left.right.value);
        Assertions.assertEquals(22,tree.getRoot().right.left.value);
        Assertions.assertEquals(29,tree.getRoot().right.right.value);
    }

    @Test
    public void deleteNodeWithChildren(){
        int[] inA = {21,18,13,19,27,22,29};
        BinaryTree tree = new BinaryTree();
        tree.buildFromArray(inA);
        Assertions.assertTrue(tree.delete(18));
        Assertions.assertEquals(19,tree.getRoot().left.value);
        Assertions.assertEquals(13,tree.getRoot().left.left.value);
        Assertions.assertTrue(tree.delete(27));
        Assertions.assertEquals(29,tree.getRoot().right.value);
        Assertions.assertEquals(22,tree.getRoot().right.left.value);
        Assertions.assertTrue(tree.delete(22));
        Assertions.assertNull(tree.getRoot().right.left);
    }

}
