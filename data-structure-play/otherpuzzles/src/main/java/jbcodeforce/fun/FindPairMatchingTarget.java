package jbcodeforce.fun;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;


public class FindPairMatchingTarget {

   static List<Pair<Integer,Integer>> solution = new ArrayList<Pair<Integer,Integer>>();

   public static void main(String[] args) {
    int[] A = {1, 4, 7, 10};
    int[] B = {4, 5, 7, 8};
    int target = 13;
    
    //take top left corner of the matrix [B,A]
    int i = B.length - 1;
    int j = 0;
    while (i >= 0) {
       int sum = B[i] + A[j];
       System.out.println(sum);
       if (sum == target) {
          solution.add(new Pair<Integer,Integer>(A[j],B[i]));
          i--; //arbitrary
       } else {
          addCloseNeighboors(sum,target,A[j], B[i]);
          if (sum > target) {
             i--;
          } else {
             j++;
          }
       }
    }
    System.out.println(solution.toString());
   } 

   public static void addCloseNeighboors(int sum, int target, int a, int b) {
      if ((sum + 1) == target) {
         solution.add(new Pair<Integer,Integer>(a,b));
      }
      if ((sum - 1) == target) {
         solution.add(new Pair<Integer,Integer>(a,b));
      }
   }
}
