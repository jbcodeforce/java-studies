package jbcodeforce.fun.search;

public class BinarySearch {
    
    public int find(int[] sortedArray,int value) {
        return findInRange(sortedArray,value,0,sortedArray.length -1);
    }

    public int findInRange(int[] sortedArray,int value,int lowerIdx ,int higherIx) {
        int idx = (lowerIdx + higherIx) / 2;
        if (sortedArray[idx] == value) {
            return idx;
        } else if (lowerIdx > higherIx) return -1;
        
        if (sortedArray[idx] > value) {
            return findInRange(sortedArray, value, lowerIdx, idx - 1 );
        } else {
            return findInRange(sortedArray, value, idx + 1, higherIx );
        }
    }
}
