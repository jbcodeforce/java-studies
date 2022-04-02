package jbcodeforce.fun.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBinarySearch {
    
    @Test
    public void testSimplestArray(){
        int[] a = {1};
        BinarySearch search = new BinarySearch();
        Assertions.assertEquals(0,search.find(a,1));
        Assertions.assertEquals(-1,search.find(a,2));
    }

    @Test
    public void testTwoElementArray(){
        int[] a = {1,2};
        BinarySearch search = new BinarySearch();
        Assertions.assertEquals(0,search.find(a,1));
        Assertions.assertEquals(1,search.find(a,2));
        Assertions.assertEquals(-1,search.find(a,3));
    }

    @Test
    public void test10ElementArray(){
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        BinarySearch search = new BinarySearch();
        Assertions.assertEquals(0,search.find(a,1));
        Assertions.assertEquals(4,search.find(a,5));
        Assertions.assertEquals(9,search.find(a,10));
        Assertions.assertEquals(-1,search.find(a,15));
    }

    @Test
    public void test9ElementArray(){
        int[] a = {1,2,3,4,5,6,7,8,9};
        BinarySearch search = new BinarySearch();
        Assertions.assertEquals(0,search.find(a,1));
        Assertions.assertEquals(4,search.find(a,5));
        Assertions.assertEquals(8,search.find(a,9));
        Assertions.assertEquals(-1,search.find(a,15));
    }
}
