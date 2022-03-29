package jbcodeforce.fun.maxpathsum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jbcodeforce.fun.maxpathsum.MaxPathSum;
import jbcodeforce.fun.maxpathsum.Node;

public class TestMaxPathSum {

    @Test 
    public void testOnNode() {
        Node tree = new Node(1,null,null);
        Assertions.assertEquals(1,MaxPathSum.maxPathSum(tree));
    }


    @Test 
    public void testTwoNodes() {
        Node leftLeaf = new Node(3,null,null);
        Node tree = new Node(1,leftLeaf,null);
        Assertions.assertEquals(4,MaxPathSum.maxPathSum(tree));
    }

    @Test 
    public void testThreeNodes() {
        Node leftLeaf = new Node(3,null,null);
        Node rightLeaf = new Node(4,null,null);
        Node tree = new Node(1,leftLeaf,rightLeaf);
        Assertions.assertEquals(5,MaxPathSum.maxPathSum(tree));
    }
}
