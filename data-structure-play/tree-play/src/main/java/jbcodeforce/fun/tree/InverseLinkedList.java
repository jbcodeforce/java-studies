package jbcodeforce.fun.tree;

/**
 * Given a list like a -> b -> c -> d -> e 
 * reverse it to e -> d -> c -> b -> a 
 */
public class InverseLinkedList {

    public static Node createList(){
        Node a = new Node("a");
        a.next = new Node("b");
        a.next.next = new Node("c");
        Node d = new Node("d");
        a.next.next.next = d;
        Node e = new Node("e");
        d.next = e;
        return a;
    }
    public static void main(String[] args) {
        // build linked list a,b,c,d,e
        Node inList = createList();
        Node current = inList;
        Node previous = null;
        System.out.println(inList.toString());

        while (current != null) {
            Node nextOfCurrent = current.next;
            current.next = previous;
            previous = current;
            current = nextOfCurrent;
        }
        Node reversedList = previous;
        "e,d,c,b,a".equals(reversedList.toString());
    }
}
