package jbcodeforce.fun.maxpathsum;

public class Node {
    int value;
    Node right;
    Node left;

    public Node(int v){
        right = null;
        value = v;
        left = null;
    }

    public Node(int v, Node l, Node r) {
        this.value = v;
        this.left = l;
        this.right = r;
    }
}
