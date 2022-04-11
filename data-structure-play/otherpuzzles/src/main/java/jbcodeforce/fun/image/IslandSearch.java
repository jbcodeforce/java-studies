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
     * Keep groups connected to a border.
     * Keep the island connected to border
     * 
     * @return
     */
    public int[][] removeIslands() {
        boolean[][] visited = new boolean[upperRowLimit][upperColumnLimit];
        // search cap from top row
        for (int c = 0; c < upperColumnLimit; c++) {
            if (image[0][c] == 1 && !visited[0][c]) {
                searchConnected(0, c, visited, true);
            }
        }
        // bottom row
        for (int c = 0; c < upperColumnLimit; c++) {
            if (image[upperRowLimit-1][c] == 1 && !visited[upperRowLimit-1][c]) {
                searchConnected(upperRowLimit-1, c, visited, true);
            }
        }
        // search for the cap from right border
        for (int r = 0; r <upperRowLimit; r++) {
            if (image[r][upperColumnLimit-1] == 1 && ! visited[r][upperColumnLimit-1]) {
                searchConnected(r, upperColumnLimit-1, visited, true);
            }
        }
        // search for the cap from left border
        for (int r = 0; r <upperRowLimit; r++) {
            if (image[r][0] == 1 && ! visited[r][0]) {
                searchConnected(r, 0, visited, true);
            }
        }
        // remove middle island
        for (int r = 1; r < upperRowLimit-1; r++) {
            for (int c = 1; c < upperColumnLimit-1; c++) {
                if (image[r][c] == 1 && !visited[r][c]) {
                    searchConnected(r, c, visited, false);
                }
            }
        }
        return image;
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
                    searchConnected(r, c, visited, true);
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
     * @param c       column of the current cell to seach for neighbours
     * @param visited position so far
     */
    public void searchConnected(int r, int c, boolean[][] visited, boolean keep) {
        visited[r][c] = true;
        for (int k = 0; k < rowNeighbours.length; k++) {
            int row = r + rowNeighbours[k];
            int col = c + colNeighbours[k];
            if (validPosition(row, col, visited)) {
                // neighbour is part of the island
                searchConnected(row, col, visited, keep);               
            }
        }
        if (!keep) {
            image[r][c] = 0;
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
