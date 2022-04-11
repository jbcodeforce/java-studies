package jbcodeforce.fun.basic;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;

/**
 * Use a better structure with less memory by using hashCode of row, col, square
 */
public class ValidateSudokuBetter {
    

    public static boolean isValidSudoku(char[][] board) {
        HashSet<Integer> hashCodes = new HashSet<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int hashCodeRow = 100 + r * 10 + board[r][c]; // 101 to 189
                    int hashCodeColumn = 200 + c * 10 + board[r][c]; // 201 to 289
                    int hashCodeSquare = 1000 + (r / 3)*100 + (c / 3)*10 + board[r][c]; // 1001 to 1229
                    if ( ! hashCodes.add(hashCodeRow) || ! hashCodes.add(hashCodeColumn) || ! hashCodes.add(hashCodeSquare))
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' }, { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' }, { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' }, { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' }, { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        Assertions.assertTrue(isValidSudoku(board));
        char[][] board2 = {
            { '8', '3', '.', '.', '7', '.', '.', '.', '.' }, { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
            { '.', '9', '8', '.', '.', '.', '.', '6', '.' }, { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
            { '4', '.', '.', '8', '.', '3', '.', '.', '1' }, { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
            { '.', '6', '.', '.', '.', '.', '2', '8', '.' }, { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
            { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        Assertions.assertFalse(isValidSudoku(board2));
    }
}
