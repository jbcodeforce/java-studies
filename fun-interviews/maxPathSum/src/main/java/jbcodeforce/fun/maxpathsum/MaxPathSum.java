package jbcodeforce.fun.maxpathsum;

public class MaxPathSum {

    /*
    * At a given node, the result is the max for children branches + local value
     */
    public static int maxPathSum(Node tree) {
        int leftResult = 0;
        int rightResult = 0;

        if (tree.left == null && tree.right == null)
            return tree.value;
        if (tree.left != null) {
            leftResult = maxPathSum(tree.left) + tree.value;
        }
        if (tree.right != null) {
            rightResult = maxPathSum(tree.right) + tree.value;
        }
        tree.result = Math.max(leftResult,rightResult);
        return tree.result;
    }
    
}
