package jbcodeforce.fun.basic;

import java.util.Stack;

import org.junit.jupiter.api.Assertions;

/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 * The number of nodes in the list is in the range [1, 10^5].
0 <= Node.val <= 9
 */
public class Palindrome {
    
    public Stack<ListNode>stack;

    public Palindrome(){
        stack = new Stack<>();
    }
    public boolean isPalindrone(ListNode head) {

        buildStack(head);
        boolean palindrome = true;
        ListNode current  = head;
        while (palindrome && ! stack.isEmpty()) {
            ListNode endPile = stack.pop();
            if (endPile.val != current.val) {
                palindrome = false;
            } else {
                current = current.next;
            }
        }
        clearStack();      
        return palindrome;
    }

    public void clearStack(){
        if (!stack.isEmpty()) {
            stack.clear();
        } 
    }

    public void buildStack(ListNode head){
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
    }

    public ListNode buildFifo(ListNode current){
        if (current == null) return null;
        ListNode previous = null;
        while (current != null) {
            ListNode nextOfCurrent = current.next;
            current.next = previous;
            previous = current;
            current = nextOfCurrent;
        }
        return previous;
    }

    /**
     * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
     * @param head
     * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
     * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
     * The twin sum is defined as the sum of a node and its twin.
     * @return max
     */
    public int maxTwinSum(ListNode head,int n){
        int max = Integer.MIN_VALUE;
        ListNode current  = head;
        clearStack();
        for (int i=0;i < n/2;i++){
            current = current.next;
        }
        ListNode reverseHead = buildFifo(current);
        current  = head;
        while( reverseHead != null ) {
            int sum = current.val + reverseHead.val;
            if (sum > max) {
                max = sum;
            }
            current = current.next;
            reverseHead = reverseHead.next;
        }
        return max;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2));
        Palindrome validator = new Palindrome();
        Assertions.assertFalse(validator.isPalindrone(head));
        head = new ListNode(1, new ListNode(2,new ListNode(1)));
        Assertions.assertTrue(validator.isPalindrone(head));
        head=new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        Assertions.assertTrue(validator.isPalindrone(head));
        Assertions.assertEquals(6,validator.maxTwinSum(head,4));
    }

}
