package jbcodeforce.fun.hat;

public class HashArraysTree {
    int size;
    int power;
    int currentTop;
    int currentLeaf;
    private int[][] top;

    public HashArraysTree(int power) {
        this.power = power;
        this.size = 2 << (power - 1);
        this.currentTop = 0;
        this.currentLeaf = 0;
        this.top = new int[size][size];
    }
    
    public int getFrom(int idx){
        int top_idx = idx >> power;
        int leaf_idx = idx & ((2 << (power-1)) -1);
        return top[top_idx][leaf_idx];
    }

    public void add(int value) {
        if (currentTop >= size) {
            resize();
        }
        top[currentTop][currentLeaf] = value;
        currentLeaf++;
        if (currentLeaf >= size) {
            currentTop++;
            currentLeaf = 0;
        }
        
    }

    public int resize(){
        this.power++;
        int newSize = 2 << (this.power - 1);
        int[][] tmp_top = new int[newSize][newSize];
        currentTop=0;
        currentLeaf=0;
        for(int t = 0; t<this.size;t++) {
            for (int l = 0; l< this.size;l++) {
                tmp_top[currentTop][currentLeaf] = this.top[t][l];
                currentLeaf++;
            }
            if (currentLeaf >= newSize) {
                currentTop++;
                currentLeaf=0;
            }
        }
        top = tmp_top;
        size = newSize;
        return newSize;
    }
}
