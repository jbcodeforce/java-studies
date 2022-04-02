package jbcodeforce.fun.graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDFS {
    
    @Test
    public void testTreeDFSWithPreOrderSimplestTree(){
        Node root = new Node("a");
        root.left = new Node("b");
        root.right = new Node("c");
        List<Node> result = new ArrayList<Node>();
        Node.traversePreOrder(root, result);
        Assertions.assertEquals("a",result.get(0).name);
        Assertions.assertEquals("b",result.get(1).name);
        Assertions.assertEquals("c",result.get(2).name);
    }

    @Test
    public void testTreeDFSWithPreOrderBiggerTree(){
        Node root = new Node("a");
        root.left = new Node("b");
        root.right = new Node("c");
        root.left.left = new Node("d");
        root.left.left.left = new Node("e");
        root.left.right = new Node("f");
        List<Node> result = new ArrayList<Node>();
        Node.traversePreOrder(root, result);
        Assertions.assertEquals("a",result.get(0).name);
        Assertions.assertEquals("b",result.get(1).name);
        Assertions.assertEquals("d",result.get(2).name);
        Assertions.assertEquals("e",result.get(3).name);
        Assertions.assertEquals("f",result.get(4).name);
        Assertions.assertEquals("c",result.get(5).name);
        System.out.println(result);
    }

    @Test
    public void testTreeDFSWithInOrderBiggerTree(){
        Node root = new Node("a");
        root.left = new Node("b");
        root.right = new Node("c");
        root.left.left = new Node("d");
        root.left.left.left = new Node("e");
        root.left.right = new Node("f");
        List<Node> result = new ArrayList<Node>();
        Node.traverseInOrder(root, result);
        Assertions.assertEquals("e",result.get(0).name);
        Assertions.assertEquals("d",result.get(1).name);
        Assertions.assertEquals("b",result.get(2).name);
        Assertions.assertEquals("f",result.get(3).name);
        Assertions.assertEquals("a",result.get(4).name);
        Assertions.assertEquals("c",result.get(5).name);
        System.out.println(result);
    }

    @Test
    public void testTreeDFSWithPostOrderBiggerTree(){
        Node root = new Node("a");
        root.left = new Node("b");
        root.right = new Node("c");
        root.left.left = new Node("d");
        root.left.left.left = new Node("e");
        root.left.right = new Node("f");
        List<Node> result = new ArrayList<Node>();
        Node.traversePostOrder(root, result);
        Assertions.assertEquals("e",result.get(0).name);
        Assertions.assertEquals("d",result.get(1).name);
        Assertions.assertEquals("b",result.get(2).name);
        Assertions.assertEquals("f",result.get(3).name);
        Assertions.assertEquals("c",result.get(4).name);
        Assertions.assertEquals("a",result.get(5).name);
        System.out.println(result);
    }


    @Test
    public void testGraphDFSSimple(){
        Graph a = new Graph("a");
        Graph b = new Graph("b");
        Graph c = new Graph("c");
        a.vertices.add(b);
        a.vertices.add(c);
        b.vertices.add(c);
        List<Graph> result = new ArrayList<Graph>();
        result = Graph.dfs(a);
        System.out.println(result);
    }

    @Test
    public void testGraphDFSBigger(){
        Graph a = new Graph("a");
        Graph b = new Graph("b");
        Graph c = new Graph("c");
        a.vertices.add(b);
        a.vertices.add(c);
        Graph d = new Graph("d");
        Graph e = new Graph("e");
        b.vertices.add(d);
        b.vertices.add(e);
        Graph f = new Graph("f");
        d.vertices.add(f);
        Graph g = new Graph("g");
        f.vertices.add(g);
        e.vertices.add(g);
        c.vertices.add(g);
        List<Graph> result = Graph.dfs(a);
        System.out.println(result);
        Assertions.assertEquals("f",result.get(3).name);
    }
}
