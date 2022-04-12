package jbcodeforce.fun.tree;

/**
 * The max heap is a complete binary tree with root having the highest value of the collection
 * A Full Binary Tree follows some simple rules:

* - if a node has only one child, that should be its left child 
* - only the rightmost node on the deepest level can have exactly one child
* - leaves can only be on the deepest level
 */
public class MaxHeap {
    IntNode root;

    public MaxHeap(){
    }

    /**
     * - create a leaf as the rightmost slot on the deepest level
     * - if the leaf value > than its parent, swap them
     * - continue until reaching root or the node.value < parent
     * @param val
     */
    public void insert(int val) {
        if (root == null) {
            root = new IntNode(val);
            return;
        }  
        IntNode parent = root;
        parent=findRightMostnode(parent);
        IntNode newLeaf = new IntNode(val);
        if (parent.left == null) {
            parent.left = newLeaf;
            newLeaf.parent = parent;
            swapIfNeeded(parent,newLeaf);
        
        } else {
            parent.right = newLeaf;
            newLeaf.parent = parent;
            swapIfNeeded(parent,newLeaf);
        }
       
     }

     public IntNode getRoot() {
         return root;
     }


     public IntNode findRightMostnode(IntNode current) {
        if (current.left == null || current.right == null) return current;
       return findRightMostnode(current.left);
     }

     /**
      * 
      * @param parent
      * @param child
      * @return new parent
      */
     public IntNode swapTwoNode(IntNode parent, IntNode child) {
        IntNode pr = parent.right;
        IntNode pl = parent.left;
        IntNode cr = child.right;
        IntNode cl = child.left;
        child.parent = parent.parent;
        if (parent.parent != null) {
            if (parent.parent.right == parent)
                parent.parent.right = child;
            else 
                parent.parent.left = child;
        }
        parent.parent = child;
        parent.left = cl;
        parent.right = cr;
        if (cr != null) cr.parent = parent;
        if (cl != null) cl.parent = parent;
        if (pl == child) {
            child.left = parent;
            child.right = pr;
            if (pr != null) pr.parent = child;
        } else {
            child.right = parent;
            child.left = pl;
            if (pl != null) pl.parent = child;
        }
        if (child.parent == null) root=child;
        return child;
     }

     private void swapIfNeeded(IntNode parent,IntNode child) {
        if (parent == null) return;
         if (child.value > parent.value) {
             swapTwoNode(parent, child);
             swapIfNeeded(child.parent, child);
         }
     }

     public void print() {
        traverseInOrder(root);
        System.out.println();
     }

     public void traverseInOrder(IntNode current) {
        if (current != null ){
            traverseInOrder(current.left);
            visit(current);
            traverseInOrder(current.right);
        }
     }

     public void visit(IntNode n) {
        System.out.print(" " + n.value);
        
     }
}
