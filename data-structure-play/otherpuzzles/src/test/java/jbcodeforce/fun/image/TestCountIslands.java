package jbcodeforce.fun.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCountIslands {
    
    @Test
    public void shouldHave5Islands(){
        int[][] image = {{1, 1, 0, 0, 0},
                         {0, 1, 0, 0, 1},
                         {1, 0, 0, 1, 1},
                         {0, 0, 0, 0, 0},
                         {1, 0, 1, 0, 1}};
        IslandSearch search = new IslandSearch(image);
        Assertions.assertEquals(5, search.countIslands());
    }

    @Test
    public void shouldHave1Island(){
        int[][] image = {{1, 1, 1, 0, 0},
                         {0, 1, 1, 0, 1},
                         {1, 0, 1, 1, 1},
                         {0, 1, 0, 0, 0},
                         {1, 0, 1, 0, 0}};
        IslandSearch search = new IslandSearch(image);
        Assertions.assertEquals(1, search.countIslands());
    }
}
