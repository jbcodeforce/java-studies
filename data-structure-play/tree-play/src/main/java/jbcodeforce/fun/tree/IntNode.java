package jbcodeforce.fun.tree;

public class IntNode {
    int value;
    IntNode left;
    IntNode right;
    int accumulatedSum;

    public IntNode(int v){
        this.value = v;
        this.accumulatedSum = v;
    }
}
