package jbcodeforce.fun.snapshotarray;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BetterSolution {

    List<TreeMap<Integer, Integer>> source;
    int currId = 0;

    public BetterSolution(int length) {
        source = new ArrayList<TreeMap<Integer, Integer>>();
        
        for (int i = 0; i < length; i++) {
            source.add(i, new TreeMap<Integer, Integer>());
            source.get(i).put(0, 0);
        }
    }
    
    public void set(int index, int val) {
        source.get(index).put(currId, val);
    }
    
    public int snap() {
        return currId++;
    }
    
    public int get(int index, int snap_id) {
        return source.get(index).floorEntry(snap_id).getValue();
    }
}
