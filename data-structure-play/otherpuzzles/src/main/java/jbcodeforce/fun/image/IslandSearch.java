package jbcodeforce.fun.image;

/**
 * Given a black 1 and white 0 image, as a matrix,
 * remove islands in the image. Island are group of 1 not connected to
 * the borders.
 */
public class IslandSearch {

    // used to search the 8 neiboors of a pixel at (row,col)
    static int[] rowNeighbours = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] colNeighbours = { -1, 0, 1, -1, 1, -1, 0, 1 };
    int upperRowLimit;
    int upperColumnLimit;

    int[][] image;

    public IslandSearch(int[][] image) {
        this.image = image;
        upperRowLimit = image[0].length;
        upperColumnLimit = image[1].length;
    }

    /**
     * Keep only 1 connected to a border
     * @return
     */
    public int[][] removeIslands() {
        boolean[][] visited = new boolean[upperRowLimit][upperColumnLimit];
        for (int r = 0; r < upperRowLimit; r++) {
            for (int c = 0; c < upperColumnLimit; c++) {
                if (image[r][c] == 1 && ! visited[r][c]) {
                    partOfIsland(r, c,visited);
                }
            }
        }
        return image;
    }

    /**
     * At (r,c) image is 1. To be an island it needs not being connected
     * to a border
     * @param r
     * @param c
     * @return
     */
    public boolean partOfIsland(int r, int c,boolean[][] visited) {
        visited[r][c] = true;
        if (image[r - 1][c] == 0 && image[r + 1][c] == 0
                && image[r][c - 1] == 0 && image[r][c + 1] == 0) {
            return true;
        }
        // may be part of potential island
        return false;
    }

    /**
     * Count the number of connected group of 1 in the images.
     * Connected means there is a 1 in the 8 possibles neighbours.
     * 
     * The 2D matrix of 1 and 0, can be seen as a graph of neighbours, so to
     * get the island we use DFS to search for connected group.
     * 
     * We pass one time into the matrix, and then looks at neighbour
     * 
     * @return the number of island.
     */
    public Integer countIslands() {
        boolean[][] visited = new boolean[upperRowLimit][upperColumnLimit];
        int count = 0;
        for (int r = 0; r < upperRowLimit; r++) {
            for (int c = 0; c < upperColumnLimit; c++) {
                if (image[r][c] == 1 && !visited[r][c]) {
                    searchConnected(r, c, visited);
                    count++;
                }
            }
        }
        return count;
    }// countIslands

    /**
     * Depth first search for neighbours
     * 
     * @param r       row of where to look at neighbours
     * @param c       column
     * @param visited pixel so far
     */
    public void searchConnected(int r, int c, boolean[][] visited) {
        visited[r][c] = true;
        for (int k = 0; k < rowNeighbours.length; k++) {
            int row = r + rowNeighbours[k];
            int col =  c + colNeighbours[k];
            if (validPosition(row, col, visited))
                searchConnected(row,col, visited);
        }
    }

    boolean validPosition(int row, int col,
            boolean visited[][]) {
        // row number is in range, column number is in range
        // and value is 1 and not yet visited
        return (row >= 0) && (row < upperRowLimit) 
                && (col >= 0) && (col < upperColumnLimit) 
                && (image[row][col] == 1 && !visited[row][col]);
    }
}
