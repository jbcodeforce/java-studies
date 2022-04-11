package jbcodeforce.fun.sorting;

import java.util.Arrays;

/**
 * The data set is divided into small and large elements: small elements move to the left, large elements to the right. 
 * Each of the created partitions is then recursively partitioned again until a partition contains only one element and 
 * is therefore considered sorted.
 * https://www.happycoders.eu/algorithms/quicksort/
 */
public class QuickSort {
 
    public static int[] sort(int[] a){
        quicksort(a,0,a.length-1);
        return a;
    }

    public static void quicksort(int[] a,int left,int right) {
        if (left >= right) return;

        int pivotPos = partition(a, left, right);
        quicksort(a, left, pivotPos - 1);
        quicksort(a, pivotPos + 1, right);
    }

    public static int partition(int[] a, int leftIdx, int rightIdx){
        int pivot = a[rightIdx];
        int i = leftIdx;
        int j = rightIdx - 1;
        // find element in the left bigger than pivot
        while (i < j ) {
            while (a[i] < pivot) {
                i++;
            }
            while (j > leftIdx && a[j] >= pivot) {
                j--;
            }
            if (i<j) {
                swapAtTwoPositions(a,i,j);
                i++;
                j--;
            }
        }
        if (i == j && a[i] < pivot) {
            i++;
        }
        // Move pivot element to its final position
        if (a[i] != pivot) {
            swapAtTwoPositions(a,i,rightIdx);
        }
        return i;
    }

    public static void swapAtTwoPositions(int[] a, int left, int right){
        int p = a[right];
        a[right] = a[left];
        a[left] = p;
    }

    public static void main(String[] args) {
        int[] a = {2,5,1,7,8,3,6,9,4};
        Arrays.stream(a).forEach(System.out::print);
        System.out.println();
        QuickSort.sort(a);
        Arrays.stream(a).forEach(System.out::println);
    }
}
