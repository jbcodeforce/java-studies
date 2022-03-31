package jbcodeforce.fun.maxpathsum;

public class MaxPathSumbinaryTree {

    Node root;

    /*
    * At a given node, the result is the max path sum fomr this current node
     */
    public int maxPathSum(Node currentRootNode, Result result) {
 
        if (currentRootNode == null) return 0;
        
        int leftResult = maxPathSum(currentRootNode.left,result);
        int rightResult = maxPathSum(currentRootNode.right,result);
        // max path sum of this node for a parent of this node
        int max_single = Math.max(Math.max(leftResult, rightResult) + currentRootNode.value, currentRootNode.value);
   
        int max_top = Math.max(max_single, leftResult + rightResult + currentRootNode.value);
        // Store the Maximum Result.
        result.value = Math.max(result.value, max_top);

        return max_single;
    }
    
    public int findMaxSum() {
        return findMaxSum(root);
    }

    public int findMaxSum(Node currentRootNode) {
        Result res = new Result();
        res.value = Integer.MIN_VALUE;
        maxPathSum(root,res);
        return res.value;
    }

    class Result {
        public int value;
    }
}
