package jbcodeforce.fun.tree;

public class Node {
    String value;
    Node next;

    public Node(String v) {
        value = v;
        next = null;
    }

    public Node(String v, Node aNext) {
        value = v;
        next = aNext;
    }

    public String toString() {
        return buildListAsString(this);
    }

    public String buildListAsString(Node fromNode) {
        if (fromNode == null) return "";
        return fromNode.value + "," + buildListAsString(fromNode.next);
    }
}
