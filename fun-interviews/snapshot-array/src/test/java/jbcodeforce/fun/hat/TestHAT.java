package jbcodeforce.fun.hat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestHAT {
    
    @Test
    public void testCreateHAT() {
        // 2^power for the size
        HashArraysTree hat = new HashArraysTree(1);
        Assertions.assertEquals(2,hat.size);
        hat.add(10);
        hat.add(20);
        Assertions.assertEquals(10, hat.getFrom(0));
        Assertions.assertEquals(20, hat.getFrom(1));
        Assertions.assertEquals(0, hat.getFrom(2));
    }

    @Test
    public void testResizeHAT() {
        // 2^power for the size
        HashArraysTree hat = new HashArraysTree(1);
        Assertions.assertEquals(2,hat.size);
        for (int i = 0; i< 6;i++) {
            hat.add(i);
        }
        Assertions.assertEquals(4,hat.size);
        Assertions.assertEquals(0, hat.getFrom(0));
        Assertions.assertEquals(3, hat.getFrom(3));
        Assertions.assertEquals(5, hat.getFrom(5));
    }

    @Test
    public void verifyPower(){
        

        Assertions.assertEquals(2,2 << 0);
        // shift 1 left 010 -> 100
        Assertions.assertEquals(4,2 << 1);
        Assertions.assertEquals(8,2 << 2);
        // 1000 - 1 = 0111
        Assertions.assertEquals(7,(2 << 2) -1);
        System.out.println(5 & ((2 << 2) -1));
        // 011 & 111 = 011
        Assertions.assertEquals(1,5 & ((2 << 2-1) -1));
        // size 4 :0,1,2,3 -> idx = 4 means 0 
        // 100 & 011 = 0  and 101 & 011 = 001
       
    }
}
