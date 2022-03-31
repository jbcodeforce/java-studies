package jbcodeforce.fun;

import java.util.TreeMap;

import org.junit.jupiter.api.Test;

public class TestTreeMap {
    
    @Test
    public void testTreeCreation(){
        TreeMap<Integer, String> tree_map
            = new TreeMap<Integer, String>();
 
        // Mapping string values to int keys
        // using put() method
        tree_map.put(10, "Geeks");
        tree_map.put(15, "4");
        tree_map.put(20, "Geeks");
        tree_map.put(25, "Welcomes");
        tree_map.put(30, "You");
 
        // Printing the elements of TreeMap
        System.out.println("TreeMap: " + tree_map);
        System.out.println(tree_map.floorEntry(18));
    }
}
