package jbcodeforce.fun.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Minimize the distance from your appartement in one of the block and the facilities you want.
 * reqs = {"gym","school","store"} is your requirements.
 * Block list if the requirements are part of the block or not.
 * Farther from one of your requirements to the block where you will live.
 * 
 * This implementation uses HashMap, and it may not be optimized. One approach is to 
 * encode the problem: so gym, school,... are int, and always positionned in the same
 * order in the requirements and in the block.
 * The data structure is a matrix a vector of block and a block being a vector of requirements.
 * 
 * We need to go one way to compute the distance with previoud block (left one), but also
 * from right to left to compute other distance with previous block (the right one).
 * 
 * There is a better solution than computing the min block | distance done here.
 */
public class SearchBestBlock {

    /**
     * Example of city with list of blocks
     * @return
     */
    public static List<HashMap<String,Boolean>> buildBlocks() {
        List<HashMap<String,Boolean>> blocks = new ArrayList<HashMap<String,Boolean>>();
        HashMap<String,Boolean> block0 = new HashMap<String,Boolean>();
        block0.put("gym", false);
        block0.put("school", true);
        block0.put("store", false);
        blocks.add(block0);
        HashMap<String,Boolean> block1 = new HashMap<String,Boolean>();
        block1.put("gym", true);
        block1.put("school", false);
        block1.put("store", false);
        blocks.add(block1);
        HashMap<String,Boolean> block2 = new HashMap<String,Boolean>();
        block2.put("gym", true);
        block2.put("school", true);
        block2.put("store", false);
        blocks.add(block2);
        HashMap<String,Boolean> block3 = new HashMap<String,Boolean>();
        block3.put("gym", false);
        block3.put("school", true);
        block3.put("store", false);
        blocks.add(block3);
        HashMap<String,Boolean> block4 = new HashMap<String,Boolean>();
        block4.put("gym", false);
        block4.put("school", true);
        block4.put("store", true);
        blocks.add(block4);
        return blocks;
    }

    public static String[] requirements = {"gym","school","store"};

    public static HashMap<String,Integer> buildDistance(){    
        HashMap<String,Integer> currentDistance = new HashMap<String,Integer>();
        for (String s : requirements) {
            currentDistance.put(s, Integer.MAX_VALUE);
        }
        return currentDistance;
    }
    
    public static void computeDistanceSoFar(HashMap<String,Boolean> block,HashMap<String,Integer> currentDistance ){
        // loop on requirements
        for (String k: currentDistance.keySet()) {
            // found a matching requirement in current block
            if (block.get(k)) {
                currentDistance.put(k, 0);
            } else {
                // requirement not in this block so may be in previous block so increase distance
                if (currentDistance.get(k) < Integer.MAX_VALUE) {
                    currentDistance.put(k,currentDistance.get(k) + 1);
                }
            }
        }
    } 
    
    public static HashMap<String,Integer> cloneDistance(HashMap<String,Integer> d) {
        HashMap<String,Integer> newD = new HashMap<String,Integer>();
        for ( String k: d.keySet()) {
            newD.put(k, d.get(k));
        }
        return newD;
    }

    public static HashMap<String,Integer> mergeDistances(HashMap<String,Integer> newD,HashMap<String,Integer> toMergeTo){
        for ( String key: newD.keySet()) {
            if (newD.get(key) < toMergeTo.get(key)) {
                toMergeTo.put(key, newD.get(key));
            }
            
        }
        return toMergeTo;
    }
    
    public static void printResult(List<HashMap<String,Integer>> blockAssessments) {
        for (HashMap<String,Integer> blockAssessment:  blockAssessments) {
            for (String k : blockAssessment.keySet()) {
                System.out.println(k + " " + blockAssessment.get(k));
            }
            System.out.println("--------");
        }
    }

    // each block has a score for each requirement. The minimum farther will be zero, then 1...
    public static int minimizeFartherDistance(List< HashMap<String,Integer>> blockAssessments){
        for (int i = 0; i < blockAssessments.size();i++) {
            for (int j=0;j < blockAssessments.size();j++) {
                int sum = 0;
                for (Integer k : blockAssessments.get(j).values()) {
                    int d= (k-i);
                    if (d > 0)
                        sum = sum + d; 
                }
                if (sum == 0) return j;
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * The main algo: go over the blocks from left to right - compute distance for each block
     * then go from right to left.
     */
    public static void main(String[] args) {
        
        List<HashMap<String,Boolean>>  blocks = buildBlocks();
        // keep the list of distances for each block to evaluate the minimum
        List< HashMap<String,Integer>> blockAssessments = new ArrayList<HashMap<String,Integer>>();
        
        HashMap<String,Integer> currentDistance = buildDistance();
        // loop over the blocks in one direction
        for (int i = 0; i< blocks.size();i++) {
            HashMap<String,Boolean> block = blocks.get(i);
            computeDistanceSoFar(block,currentDistance);
            blockAssessments.add(cloneDistance(currentDistance));
        }
        printResult(blockAssessments);
        //do the reverse path but merge with current result
        currentDistance = buildDistance();
        for (int i = blocks.size() -1; i>=0;i--) {
            HashMap<String,Boolean> block = blocks.get(i);
            computeDistanceSoFar(block,currentDistance);
            mergeDistances(currentDistance,blockAssessments.get(i));
        }
        
        printResult(blockAssessments);
        System.out.println(minimizeFartherDistance(blockAssessments));
        
    }
}
