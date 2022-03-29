package jbcodeforce.fun.snapshotarray;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestSnapShotArray {
 
    @Test 
    public void testArrayCreation() {
        SnapShotArray anArray = new SnapShotArray(10);
        Assertions.assertEquals(10, anArray.source.length);
        Assertions.assertNotEquals(9, anArray.source.length);
        Assertions.assertEquals(0,anArray.get(0, 0));
    }

    @Test 
    public void testAccessToValue() {
        SnapShotArray anArray = new SnapShotArray(10);
        anArray.set(1,2);
        Assertions.assertEquals(2,anArray.get(1, 0));
    }

    @Test 
    public void shouldGetTheValueFromSnapShot() {
        SnapShotArray anArray = new SnapShotArray(3);
        Assertions.assertEquals(3, anArray.source.length);
        anArray.set(0,5);  // Set array[0] = 5
        Assertions.assertEquals(5,anArray.get(0, 0));
        int snap_id = anArray.snap();  // Take a snapshot, return snap_id = 0
        Assertions.assertEquals(0, snap_id);
        Assertions.assertEquals(5,anArray.get(0, 0));
    }

    @Test 
    public void shouldGetTheValueFromSourceUntilSnapshot() {
        SnapShotArray anArray = new SnapShotArray(3);
        Assertions.assertEquals(3, anArray.source.length);
        anArray.set(0,5);  // Set array[0] = 5
        anArray.set(1,6);  // Set array[1] = 6
        Assertions.assertEquals(5,anArray.get(0, 0));
        Assertions.assertEquals(6,anArray.get(1, 0));
        int snap_id = anArray.snap();  // Take a snapshot, return snap_id = 0
        Assertions.assertEquals(0, snap_id);
        Assertions.assertEquals(5,anArray.get(0, 0));
        Assertions.assertEquals(6,anArray.get(1, 0));
        Assertions.assertEquals(0,anArray.get(2, 0));
        anArray.set(2,7);  
        Assertions.assertEquals(0,anArray.get(7, 0));
    }
}