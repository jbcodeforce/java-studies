package jbcodeforce.fun.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be
 * validated according to the following rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9
 * without repetition.
 */
public class ValidateSudoku {
    static List<HashMap<Character, Integer>> columns = new ArrayList<HashMap<Character, Integer>>(9);
    static List<HashMap<Character, Integer>> rows = new ArrayList<HashMap<Character, Integer>>(9);
    static List<HashMap<Character, Integer>> squares = new ArrayList<HashMap<Character, Integer>>(9);

    public static boolean isValidSudoku(char[][] board) {
        buildStructure();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    if (!assessNotInColumn(board, r, c))
                        return false;
                    if (!assessNotInRow(board, r, c))
                        return false;
                    if (!assessNotInSquare(board, r, c))
                        return false;
                }
            }
        }
        return true;
    }

    private static void buildStructure(){
        for (int i = 0; i < 9; i++) {
            columns.add(new HashMap<Character,Integer>());
            rows.add(new HashMap<Character,Integer>());
            squares.add(new HashMap<Character,Integer>());
        }
    } 

    private static boolean assessNotInColumn(char[][] board, int r, int c) {
        HashMap<Character, Integer> currentColumn = columns.get(c);
        if (currentColumn.containsKey(board[r][c])) {
            return false;
        } else {
            currentColumn.put(board[r][c], 1);
        }
        return true;
    }

    private static boolean assessNotInRow(char[][] board, int r, int c) {
        HashMap<Character, Integer> currentRow = rows.get(r);
        if (currentRow.containsKey(board[r][c])) {
            return false;
        } else {
            currentRow.put(board[r][c], 1);
        }
        return true;
    }


    private static boolean assessNotInSquare(char[][] board, int r, int c) {
        int x = 8;
        if ( r <= 2) {
            if (c <= 2) x = 0;
            else if ( c > 2 && c <= 5) x = 1;
                 else x = 2;
        } else if ( r > 2 && r <= 5) {
                if (c <= 2) x = 3;
                else if ( c > 2 && c <= 5) x = 4;
                     else x = 5;
            } else {
                if (c <= 2) x = 6;
                else if ( c > 2 && c <= 5) x = 7;
            }
            
        HashMap<Character, Integer> currentSquare = squares.get(x);
        if (currentSquare.containsKey(board[r][c])) {
            return false;
        } else {
            currentSquare.put(board[r][c], 1);
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
