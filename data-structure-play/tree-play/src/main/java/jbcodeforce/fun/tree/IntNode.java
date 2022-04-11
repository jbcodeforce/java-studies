package jbcodeforce.fun.tree;

public class IntNode {
    int value;
    IntNode left;
    IntNode right;
    int accumulatedSum;
    IntNode parent;

    public IntNode(int v){
        this.value = v;
        this.accumulatedSum = v;
        this.right = null;
        this.left = null;
    }
}
