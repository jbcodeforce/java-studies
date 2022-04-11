package jbcodeforce.fun.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMaxHeap {
    
    @Test
    public void testAddElement(){
        MaxHeap mh = new MaxHeap();
        mh.insert(20);
        Assertions.assertEquals(20,mh.getRoot().value);
        mh.insert(15);
        Assertions.assertEquals(20,mh.getRoot().value);
        Assertions.assertEquals(15,mh.getRoot().left.value);
        mh.print();
    }

    @Test
    public void testAddBigger(){
        MaxHeap mh = new MaxHeap();
        mh.insert(20);
        Assertions.assertEquals(20,mh.getRoot().value);
        mh.insert(25);
        Assertions.assertEquals(25,mh.getRoot().value);
        Assertions.assertEquals(20,mh.getRoot().left.value);
        mh.print();
    }

    @Test
    public void testSwapNodeInThree() {
        MaxHeap mh = new MaxHeap();
        mh.root = new IntNode(20);
        mh.root.right = new IntNode(11);
        mh.root.right.parent = mh.root; 
        mh.root.right.right = new IntNode(15);
        mh.root.right.right.parent = mh.root.right; 
        IntNode newRoot = mh.swapTwoNode(mh.root.right, mh.root.right.right);
        Assertions.assertEquals(15,newRoot.value);
        Assertions.assertEquals(20,mh.root.value);
        Assertions.assertEquals(15,mh.root.right.value);
    }

    @Test
    public void testSwapNode() {
        MaxHeap mh = new MaxHeap();
        mh.root = new IntNode(20);
        mh.root.left = new IntNode(11);
        mh.root.left.parent = mh.root; 
        mh.root.right = new IntNode(12);
        mh.root.right.parent = mh.root; 
        mh.root.right.right = new IntNode(15);
        mh.root.right.right.parent = mh.root.right; 
        mh.root.right.left = new IntNode(16);
        mh.root.right.left.parent =  mh.root.right; 
        IntNode newRoot = mh.swapTwoNode(mh.root, mh.root.right);
        Assertions.assertEquals(12,newRoot.value);
    }

    @Test
    public void testAddToFourNodes(){
        MaxHeap mh = new MaxHeap();
        mh.insert(20);
        mh.insert(10);
        mh.insert(12);
        Assertions.assertEquals(20,mh.getRoot().value);
        mh.insert(25);
        Assertions.assertEquals(25,mh.getRoot().value);
        mh.insert(30);
        Assertions.assertEquals(30,mh.getRoot().value);
        mh.print();
    }
}

