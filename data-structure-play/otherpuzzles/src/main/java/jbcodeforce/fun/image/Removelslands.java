package jbcodeforce.fun.image;

/**
 * Given a black 1 and white 0 image, as a matrix,
 * remove islands in the image. Island are group of 1 not connected to
 * the borders.
 */
public class Removelslands {
    int[][] image;

    public Removelslands(int[][] image ) {
        this.image = image;
    }

    public int[][] removeIslands(){
        for (int r = 1;r < image[0].length;r++) {
            for(int c = 1;c < image[1].length;c++) {
                if (image[r][c] == 1) {
                    if (  partOfIsland(r,c)) {
                        image[r][c]=0;
                    }
                }
            }
        }
        return image;
    }

    /**
     * At (r,c) image is 1. To be an island it needs to be rounded by 0
     * @param r
     * @param c
     * @return
     */
    public boolean partOfIsland(int r, int c) {
        if (image[r-1][c] == 0 && image[r+1][c] == 0
            && image[r][c-1] == 0 && image[r][c+1] == 0){
            return true;
        }
        // may be part of potential island
        return false;
    }
}
