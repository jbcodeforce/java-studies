package jbcodeforce.fun.basic;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int[] a) {
        ListNode l = null;
        for(int i = a.length-1;i>0;i-- ){
            l = new ListNode(a[i],l);
        }
        val=a[0];
        next = l;
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
