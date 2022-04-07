package jbcodeforce.fun.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRemoveIsland {
    
    @Test
    public void testSimplestIsland(){
        int[][] a = {{0,0,0},{0,1,0},{0,0,0}};
        Removelslands parser = new Removelslands(a);
        Assertions.assertEquals(1, a[1][1]);
        a= parser.removeIslands();
        Assertions.assertEquals(0, a[1][1]);
    }

    @Test
    public void testACape(){
        int[][] a = {{1,0,0,0},{1,0,1,1},{0,0,0,0},{0,0,0,0}};
        Removelslands parser = new Removelslands(a);
        Assertions.assertEquals(1, a[1][2]);
        a= parser.removeIslands();
        Assertions.assertEquals(1, a[1][2]);
        Assertions.assertEquals(1, a[1][3]);
    }

    @Test
    public void testABiggerImage(){
        int[][] a = {{1,0,0,0,0,0},
                     {1,0,1,1,0,0},
                     {0,1,0,0,0,0},
                     {1,1,0,0,0,0},
                     {1,0,1,1,1,0},
                     {0,0,0,0,0,0}};
        Removelslands parser = new Removelslands(a);
        Assertions.assertEquals(1, a[1][2]);
        a= parser.removeIslands();
        Assertions.assertEquals(1, a[1][2]);
        Assertions.assertEquals(1, a[1][3]);
        
    }
}
