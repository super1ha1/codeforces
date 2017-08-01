package problem_set;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static int max = 0;
    private static char[][] array;
    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));

        while (true){
            int n = sc.nextInt();
            if(n == 0){
                break;
            }
            sc.nextLine();
            array = new char[n][n];
            for(int i = 0; i < n; i++){
                String line = sc.nextLine();
                array[i] = line.trim().toCharArray();
            }
            System.out.println(process(array));
        }
    }

    private static int process(char[][] array) {
        max = 0;
        int len = array.length;
        int[][] matrix = new int[len][len];
        updateArray(matrix, 0);
        return max;
    }

    private static boolean updateArray(int[][] matrix, int count) {
        if(count == matrix.length * matrix.length){
            return true;
        }
        for(int i = 0; i <= 1; i++){
            int row = count / matrix.length;
            int col = count % matrix.length;
            matrix[row][col] = i;
            if(updateArray(matrix, count++)){
                //calculate
                if(valid(matrix)){
                    max = Math.max(max, count(matrix));
                }
            }
            matrix[row][col] = 0;
        }
        return false;
    }

    private static boolean valid(int[][] matrix) {
        for(int i = 0 ; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(matrix[i][j] == 1 && !cellValid(matrix, i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean cellValid(int[][] matrix, int row, int col) {
        if(array[row][col] == 'X'){
            return false;
        }
        //check same row
        for(int i = col -1; i >= 0; i--){
            if(array[row][i] == 'X'){
                break;
            }
            if(matrix[row][i] == 1){
                return false;
            }
        }
        for(int i = col + 1; i < matrix.length; i++){
            if(array[row][i] == 'X'){
                break;
            }
            if(matrix[row][i] == 1){
                return false;
            }
        }

        //check same col
        for(int i = row - 1; i >= 0; i--){
            if(array[i][col] == 'X'){
                break;
            }
            if(matrix[i][col] == 1){
                return false;
            }
        }
        for(int i = row + 1; i < matrix.length; i++){
            if(array[i][col] == 'X'){
                break;
            }
            if(matrix[i][col] == 1){
                return false;
            }
        }
        return true;
    }

    private static int count(int[][] matrix) {
        int count = 0;
        for(int i = 0 ; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(matrix[i][j] == 1){
                    count++;
                }
            }
        }
        return count;
    }

}

