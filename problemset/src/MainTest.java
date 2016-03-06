

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class MainTest {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [][] board = new int[n][n];
        if(solve(board, 0)){
            printBoard(board);
        }

    }

    public static void printBoard(int[][] board){
        for( int i = 0 ; i < board.length; i++){
            for( int j = 0; j < board.length; j++){
                if( board[i][j] == 1){
                    System.out.print("[#]");
                }else{
                    System.out.print("[.]");
                }
            }
            System.out.println();
        }
    }
    public static boolean solve (int[][] board, int col){
        if(col >= board.length)
            return true;
        for( int row = 0; row < board.length; row++){
            if(isSafe(board, row, col)){
                placeQueen(board, row, col);
                if( solve(board, col + 1))
                    return true;
                removeQueen(board, row, col);
            }
        }
        return false;
    }

    public static boolean isSafe(int[][] board, int currentRow, int currentCol){
        return board[currentRow][currentCol] != -1;
    }

    public static void placeQueen(int[][] board, int currentRow, int currentCol){
        board[currentRow][currentCol] = 1;
        for( int col = 0 ; col < currentCol + 1; col++){
            for( int row = 0; row < board.length; row++){
                if( board[row][col] == 1){
                    markCheck(board, row, col);
                }
            }
        }
    }

    public static void removeQueen(int[][] board, int currentRow, int currentCol){
        board[currentRow][currentCol] = 0;

        for(int row = 0; row < board.length; row++){
            for( int col =0 ;col < board.length; col++){
               if(board[row][col] != 1){
                   board[row][col] = 0;
               }
            }
        }

        for( int col = 0 ; col < currentCol; col++){
            for( int row = 0; row < board.length; row++){
                if( board[row][col] == 1){
                    markCheck(board, row, col);
                }
            }
        }
    }

    public static void markCheck(int[][] check, int row, int col){
        for(int i = 0; i < check.length; i++){
            for( int j =0 ;j < check.length; j++){
                if(i == row || j == col ||
                        Math.abs(i - row) == Math.abs(j - col)){
                    check[i][j] = -1;
                }
            }
        }
    }



}