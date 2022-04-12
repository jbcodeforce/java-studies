package jbcodeforce.fun.tree;

public class HeapSort {
    
    public void print(int[] array,int size){
        int index = 0;
        while(index < size){
            System.out.print(" " + array[index]);
            index++;
        }
    }

    /**
     * Transform the array to a heap with maximum value of the array being put into the root
     * @param array
     */
    public void heapify(int[] array, int size, int index) {
        int  maximum = index;
        int leftChild = 2*index + 1;
        int rightChild = 2*index + 2;
        int swapper;

        //now check if right and left child are greater than parent and the right and left child index are not out of bound.
        if(leftChild < size && array[leftChild] > array[maximum]){
            maximum = leftChild;
        }
        if(rightChild < size && array[rightChild] > array[maximum]){
            maximum = rightChild;
        }

        //if maximum is not equal to its initial declaration(root) then swap.
        if(maximum != index){
            swapper = array[index];
            array[index] = array[maximum];
            array[maximum] = swapper;

            //we will recursively heapify the affected sub-tree
            heapify(array,size,maximum);
        }
    }

    public void sort(int[] array) {
        int size = array.length;
        int swapper;

        int index = (size/2) - 1;
        while(index >=0){
            heapify(array,size,index);
            index--;
        }
        //We will extract elements from heap one by one and reduce size of the heap (assuming part of array is sorted controlled by index)
        for(index = size -1; index > 0; index--){
            //largest resides on root in max-heap
            swapper = array[0];
            array[0] = array[index];
            array[index] = swapper;

            //call heapify on root of reduced heap
            heapify(array,index, 0);
        }
    }

    public static void main(String args[])
    {   
        int[] array = { 3, 1, 4, 9, 8, 6 };
        int size = array.length;
        HeapSort sorter = new HeapSort();
        sorter.sort(array);
        System.out.println("After Heap Sort: ");
        sorter.print(array,size);
    }
}
