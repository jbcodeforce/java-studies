package jbcodeforce.fun.sorting;

import org.junit.jupiter.api.Assertions;

import jbcodeforce.fun.basic.ListNode;

public class MergeTwoSortedList {
    
    public static ListNode mergeSortedList( ListNode listA,  ListNode listB){   
        //return mergeSortedListIt(listA,listB);
        return mergeSortedListRec(listA,listB);
    }


    public static ListNode mergeSortedListIt( ListNode currentA,  ListNode currentB) {
        ListNode c = new ListNode();     
        ListNode currentC = c;
        while (currentA != null && currentB != null) {
                if (currentA.val <= currentB.val) {
                    currentC.next = currentA;
                    currentA = currentA.next;
                } else {
                    currentC.next = currentB;
                    currentB = currentB.next;
                    
                }
                currentC = currentC.next;
        }
        if (currentA == null) {
            currentC.next = currentB;
        } else {
            currentC.next = currentA;
        }
        return c.next;
    }


    public static ListNode mergeSortedListRec( ListNode currentA,  ListNode currentB){
        if (currentB == null) return currentA;
        if (currentA == null) return currentB;
        if (currentA.val < currentB.val) {
            currentA.next = mergeSortedListRec(currentA.next,currentB);
            return currentA;
        } else {
           
            currentB.next= mergeSortedListRec(currentA,currentB.next);
            return currentB;
            }
    }

    public static void main(String[] args) {
        System.out.println("Test 1");
        int[] a = {1};
        int[] b = {2,3,4};
        ListNode listA = new ListNode(a);
        ListNode listB = new ListNode(b);
        ListNode c = mergeSortedList(listA,listB);
        Assertions.assertEquals(1,c.val);
        Assertions.assertEquals(2,c.next.val);
        Assertions.assertEquals(3,c.next.next.val);
        // --------- 2 tests 
        System.out.println("Test 2");
        int[] a2 = {1,2,4};
        int[] b2 = {2,3,4,5};
        listA = new ListNode(a2);
        listB = new ListNode(b2);
        c = mergeSortedList(listA,listB);
        Assertions.assertEquals(1,c.val);
        Assertions.assertEquals(2,c.next.val);
        Assertions.assertEquals(2,c.next.next.val);
        Assertions.assertEquals(3,c.next.next.next.val);
        Assertions.assertEquals(4,c.next.next.next.next.val);
        System.out.println("Done");
    }
}
