package jbcodeforce.fun.maxpathsum;

public class Node {
    int value;
    Node right;
    Node left;
    int result;

    public Node(){}

    public Node(int v, Node l, Node r) {
        this.value = v;
        this.left = l;
        this.right = r;
    }
}
