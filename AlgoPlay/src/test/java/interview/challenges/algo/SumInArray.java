package interview.challenges.algo;

import org.junit.Assert;
import org.junit.Test;

/**
 * implement a method to see if given sum, it exists two numbers in an array
 * that can do this sum
 * 
 * ex In sum 8 and array [ 1, 2, 3, 9 ] returns false
 * while sum: 8 and array [1, 2, 4, 4 ] returns true
 *  
 * @author jeromeboyer
 *
 */
public class SumInArray {

	/** 
	 * loop over the array in n * n-1
	 * 
	 * @param sorted array of int
	 * @param sum
	 * @return true is it exists two numbers in a: i,j where i + j = sum
	 */
	public static boolean basicWay(int[] a, int sum) {
		for (int i = 0; i < a.length -1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] + a[j] == sum ) return true;
			}
		}
		return false;
	}
	
	@Test
	public void testO2complexity() {
		int [] a = {1,2,3,9};
		Assert.assertFalse(basicWay(a,8));
	}

}
