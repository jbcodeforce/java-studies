package jbcodeforce.fun.snapshotarray;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Snapshot an array of int.
 * See tests to see contract
 */
public class SnapShotArray {

    int[] source;
    LinkedList<LinkedList<Node>> snapshots;
    int snap_id = -1;
    /**
     * Create array of dimension n and with zero
     */
    SnapShotArray(int n) {
        source = new int[n];
        snapshots = new  LinkedList<LinkedList<Node>>();
    }
    
    void set(int index, int val) {
        source[index] = val;
    }
    
    /**
     * snapshot the array content and return the current snap_id
     * 0 <= snap_id < (the total number of times we call snap())  
     */
    int snap() {
        snap_id++;
        for (int i=0; i< source.length;i++) {
            if (source[i] != 0) {
                addOrReplace(snap_id,i,source[i]);
            }
        }
        return snap_id;
    }
    
    /**
     * return the value at the given index for a given snapshot.
     * When the snap shot is not done yet, use the source value
     * Else get the list for a given snapshot id and search for the given idx, return the matching value or zero.
     * @param index
     * @param snap_id
     * @return
     */
    int get(int index, int snap_id) {
        if (this.snap_id < snap_id) {
            return source[index];
        }
        LinkedList<Node> nodeList = snapshots.get(snap_id);
        Iterator<Node> it = nodeList.iterator();
        while (it.hasNext()) {
            Node n = it.next();
            if (n.idx == index) {
                return n.value;
            }
        }
        return 0;
    }

    /**
     * Add to the snap_id snapshot the value at index idx
     * @param snap_id
     * @param idx
     * @param val
     */
    private void addOrReplace(int snap_id,int idx, int val) {
        if (snap_id > snapshots.size()-1) {
            Node node = new Node(snap_id,idx,val);
            LinkedList<Node> listAtSnap = new LinkedList<Node>();
            listAtSnap.add(node);
            snapshots.add(snap_id, listAtSnap);
            return;
        }
        // there is an existing snapshot
        LinkedList<Node> nodeList = snapshots.get(snap_id);
        Iterator<Node> it = nodeList.iterator();
        boolean notFound = true;
        while (it.hasNext() && notFound) {
            Node node = it.next();
            if (node.idx == idx) {
                node.value = val;
                notFound = false;
            }
        }
        if (notFound) {
            Node node = new Node(snap_id,idx,val);
            nodeList.add(node);
        }
    }

    private class Node {
        int idx;
        int value;

        public Node(int snap,int id, int v) {
            this.idx = id;
            this.value = v;
        }
    }
}



