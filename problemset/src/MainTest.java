

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
                board[row][col] = 1;
                if( solve(board, col +1))
                    return true;
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isSafe(int[][] board, int currentRow, int currentCol){
        int [][] check = new int[board.length][board.length];

        for( int col = 0 ; col < currentCol; col++){
            for( int row = 0; row < board.length; row++){
                if( board[row][col] == 1){
                    markCheck(check, row, col);
                }
            }
        }
        return check[currentRow][currentCol] != -1;
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