package jbcodeforce.fun.tree;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class TestMHT {
    
    public int distanceToLeaf(MinimumHeightTrees.Node current,int max) {

        if (current.vertices.size() == 1) {
            // a leaf
            return 0;
        }
        for (MinimumHeightTrees.Node n : current.vertices) {
            int currentDistance = distanceToLeaf(n,max);
            if (currentDistance > max) {
                max = currentDistance;
            }
        }
        current.height = max;
        return max;
    }

    @Test
    public void testNodeDistance(){
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{3,5}};
        MinimumHeightTrees mhtBuilder = new MinimumHeightTrees();
        int minHeight = 100;
        HashMap<Integer, MinimumHeightTrees.Node> nodes = mhtBuilder.buildTree(6,edges);
        System.out.println(distanceToLeaf(nodes.get(0),0));
    }
}
