package jbcodeforce.fun.tree;

import org.junit.jupiter.api.Assertions;

/**
 * Calculate depth of a full Binary tree from Preorder
 * 
 * The preorder is given as a string with two possible characters. 

‘l’ denotes the leaf
‘n’ denotes internal node

The given tree can be seen as a full binary tree where every node has 0 or two children. 
The two children of a node can ‘n’ or ‘l’ or mix of both.
 */
public class TestDepthOfATree {


    public static int findDepth(String tree, int endString) {
        int index = 0;
        return findDepth(tree,endString,index);
    }

    public static int findDepth(String tree, int endString, int index) {
        if ( index == endString || tree.charAt(index) == 'l') return 0;
        // calc height of left subtree
        // (In preorder left  tree is processed before right)
        index++;
        int left = findDepth(tree, endString, index);
        index++;
        int right = findDepth(tree, endString, index);
        return Math.max(left, right) + 1;
    }

    /*    n
         / \
        l   n
           / \
          n   l
         /\
        l  l 
    */
    public static void main(String[] args) {
        String tree = "nlnnlll";
        int n = tree.length();
        Assertions.assertEquals(3,findDepth(tree, n));
    }
}
