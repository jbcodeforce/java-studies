package jbcodeforce.fun.tree;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** Sorted binary tree */
public class BinaryTree {
    IntNode root;

    public BinaryTree(IntNode r){
        root = r;
    }

    public BinaryTree(){}

    /**
     * Invariant: at node n the left branch nodes are all lower than n.value, and
     * all nodes in right branch
     * are greater or equals to n.value
     * 
     * @param n
     */
    public void insert(IntNode n) {
        if (root == null) {
            root = n;
            return;
        }
        IntNode current = root;
        while (true) {
            if (current.value > n.value) {
                if (current.left == null) {
                    current.left = n;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = n;
                    return;
                }
                current = current.right;
            }

        }

    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private IntNode addRecursive(IntNode current, int value) {

        if (current == null) {
            return new IntNode(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }


    public IntNode getRoot() {
        return this.root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        return getSizeRecursive(root);
    }

    private int getSizeRecursive(IntNode current) {
        return current == null ? 0 : getSizeRecursive(current.left) + 1 + getSizeRecursive(current.right);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(IntNode current, int value) {
        if (current == null) {
            return false;
        }

        if (value == current.value) {
            return true;
        }

        return value < current.value
          ? containsNodeRecursive(current.left, value)
          : containsNodeRecursive(current.right, value);
    }

    /**
     * When tree is sorted
     * @return
     */
    public IntNode minimum() {
        IntNode current = getRoot();
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public IntNode maximum() {
        IntNode current = getRoot();
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    private int findSmallestValue(IntNode root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public void buildFromArray(int[] inA) {
        for (int v  : inA) {
            IntNode n = new IntNode(v);
            insert(n);
        }
    }

    public boolean delete(int value) {
        if (root == null) {
            return false;
        }
        IntNode current = root;
        IntNode parent = root;
        while(current.value != value) {
            parent = current;
            if (current.value > value) {
                current = current.left;
                if (current == null) {
                    return false;
                }
            } else {
                current = current.right;
                if (current == null) {
                    return false;
                }
            }
        }
        if (parent.left == current) {
            parent.left = current.right;
            if (current.left != null) {
                parent.left.left = current.left;
            }
        } else {
            // parent.right
            parent.right = current.right;
            if (current.left != null) {
                parent.right.left = current.left;
            }
        }
        return true;
    }

    /**
     *  print out all of its root-to-leaf paths one per line
     */
    public void displayRooToLeafPaths(){
        List<IntNode> path = new ArrayList<IntNode>();
        displayRootToLeafPathsRecursively(root,path);
    }

    private void displayRootToLeafPathsRecursively(IntNode node,  List<IntNode> path) {
        if (node == null) {
            return ;
        }
        path.add(node);
        if (node.left == null && node.right == null) {
            printPath(path);
            path.remove(node);
            return;
        }
        displayRootToLeafPathsRecursively(node.left,path);
        displayRootToLeafPathsRecursively(node.right,path);
        path.remove(node);
    }

    public void printPath(  List<IntNode> path) {
        path.forEach( (i) -> System.out.print(i.value + " "));
        System.out.println();
    }


    /**
     * Horizontal print of the tree using padding and vertical Strings
     * @param sb
     * @param padding
     * @param pointer
     * @param node
     */
    public void traversePreOrderForPrinting(StringBuilder sb, 
                                        String padding, 
                                        String pointer, 
                                        IntNode node,
                                        boolean hasRightSibling) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.value);
            sb.append("\n");
            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }


            String paddingForBoth = paddingBuilder.toString();
            String pointerForRight = "└──";
            String pointerForLeft = (node.right != null) ? "├──" : "└──";
            traversePreOrderForPrinting(sb, paddingForBoth,pointerForLeft,node.left,node.right != null);
            traversePreOrderForPrinting(sb, paddingForBoth, pointerForRight, node.right, false);
        }
    }

    public void printTree(PrintStream os) {
        if (this.root == null) {
            os.print("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.value);
        String pointerForRight = "└──";
        String pointerForLeft = (root.right != null) ? "├──" : "└──";
        traversePreOrderForPrinting(sb,"",pointerForLeft, root.left,root.right != null);
        traversePreOrderForPrinting(sb,"",pointerForRight, root.right,false);
        os.print(sb.toString());
    }

     /**
     * Given a binary tree, task is to find subtree with maximum sum in tree.
     * Questions:
     * - is the tree sorted? No
     * - Is there any negative valued?
     * If all positive values the max sum is the sum of all elements in the tree. So using
     * a post order traversal will compute the max sum
     * The value of subtree rooted at current node is equal to sum of current node
     * value, left node subtree sum
     * and right node subtree sum. We can add accumulatedSum in the IntNode
     * definition
     * 
     */
    public int getMaxSumSubtree(IntNode node, IntNode maxSubTree) {
        if (node != null) {
            int lSum = getMaxSumSubtree(node.left, maxSubTree);
            int rSum = getMaxSumSubtree(node.right, maxSubTree);
            node.accumulatedSum = node.value + lSum + rSum;
            if (maxSubTree.accumulatedSum < node.accumulatedSum) {
                maxSubTree.accumulatedSum = node.accumulatedSum;
                maxSubTree.value = node.value;
                maxSubTree.left = node.left;
                maxSubTree.right = node.right;
            }
            return node.accumulatedSum;
        }
        return 0;
    }

    /**
     * Keep order of the value in the tree. Relevant when the tree is sorted
     * @param node
     */
    public void traverseInOrder(IntNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            visit(node.value);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(IntNode node) {
        if (node != null) {
            visit(node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder(IntNode node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            visit(node.value);
        }
    }

    private void visit(int value) {
        System.out.print(" " + value);        
    }

    public void traversePostOrderWithoutRecursion() {
        Stack<IntNode> stack = new Stack<>();
        IntNode prev = root;
        IntNode current = root;
        stack.push(root);

        while (current != null && !stack.isEmpty()) {
            current = stack.peek();
            boolean hasChild = (current.left != null || current.right != null);
            boolean isPrevLastChild = (prev == current.right || (prev == current.left && current.right == null));

            if (!hasChild || isPrevLastChild) {
                current = stack.pop();
                visit(current.value);
                prev = current;
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }   
    }  

    /**
     * Inverse a tree
     * @param node
     * @return
     */
    public IntNode invert(IntNode node) 
	{ 
		if (node == null) 
			return node; 

		/* recursive calls */
		IntNode left = invert(node.left); 
		IntNode right = invert(node.right); 

		/* swap the left and right pointers */
		node.left = right; 
		node.right = left; 

		return node; 
	} 

    public IntNode invert() {
        return invert(getRoot());
    }
}
