package jbcodeforce.fun.search;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * time complexity must be better than O(n log n)
 */
public class KmostFrequentElements {
    
    public static int[] assessKmostFrequentElement(int[] nums, int k) {
        //1 count frequency in array
        HashMap<Integer,Integer> frequencies = new HashMap<Integer,Integer>();
        for (int n : nums) {
            frequencies.put(n,frequencies.getOrDefault(n,0) + 1);
        }
        // 2: build a max heap using the max of two frequencies as comparator a > b then a-b>0
        Queue<Integer> heap = new PriorityQueue<Integer>( (a,b) -> frequencies.get(a) - frequencies.get(b)); 
        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n: frequencies.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();    
        }
       
        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }

    @Test
    public void testPriorityQueue(){
        // default initial capacity (11) that orders its elements according to their natural ordering.
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
  
        // Adding items to the pQueue using add()
        pQueue.add(10);
        pQueue.add(20);
        pQueue.add(15);
        pQueue.add(5);
        pQueue.stream().forEach(System.out::println);
  
        // the top element of PriorityQueue is the smallest value
        Assertions.assertEquals(5,pQueue.peek());
    }

    @Test
    public void shouldReturnUniqueElement() {
        int[] nums = {1};
        Assertions.assertEquals(nums[0], assessKmostFrequentElement(nums,1)[0]);
    }

    @Test
    public void shouldReturnTwoElement() {
        int[] nums = {1,1,1,2,2,3};
        int[] expectedResult = {1,2};
        int[] results = assessKmostFrequentElement(nums,2);
        Assertions.assertEquals(expectedResult[0], results[0]);
        Assertions.assertEquals(expectedResult[1], results[1]);
    }

    @Test
    public void shouldReturnTwoElements() {
        int[] nums = {1,1,1,2,2,3,3,4,1,4,5,2};
        int[] expectedResult = {1,2};
        int[] results = assessKmostFrequentElement(nums,2);
        Assertions.assertEquals(expectedResult[0], results[0]);
        Assertions.assertEquals(expectedResult[1], results[1]);
    }
}
