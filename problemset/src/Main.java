import java.io.*;
import java.util.*;

/*
    1) The algorithm used to solve the game problem is backtracking:
    - construct the grid using x and y coordinate, where 0 <= x <= rows -1, 0 <= y <= cols -1
    - for each cell, the snake can either go up, down or right, branch factor is 3
    - when the snake reach the last column(y == cols -1)
        + the snake could only go up or down
        + check the total sum, if sum > maxScore, update maxScore

    2) Time complexity:
        T(1, m) = O(m); T(n, 1) = O(n)
        T(n, m) = 2T(n-1, m) + T(n, m-1)
        where n is number of rows, m is number of columns
        if n == m, we could approximate:
        T(0) = 1
        T(n) = 3 T(n-1)
        => T(n) = 3^n

    3) Advantage and disadvantage of the algorithm:
    - advantage: easy to understand, straight forward
    - disadvantage:
        + the algorithm use many recursive calls, may exceed the maximum stack size
        + time complexity is large
 */

public  class Main
{
    public static final int SIZE = 501;
    static int grid[][];
    static boolean visited[][];
    static int numRows, numCols;
    static long maxScore = -1;
    static final int increaseX[] = {-1, 0, 1}; //increase in x when the snake go up, down, right
    static final int increaseY[] = {0, 1, 0};  //increase in y when the snake go up, down, right

    public static void main (String args[])
    {
        // TODO: Implement your program
        grid = new int[SIZE][SIZE];
        visited = new boolean[SIZE][SIZE];

        Scanner sc = null;

        try {
            FileWriter writer = new FileWriter("in.txt");
            writer.write("500 500\n");
            for ( int i = 0 ; i < SIZE; i++){
                String s = "";
                for ( int j = 0; j < SIZE; j++){
                    s+= String.valueOf(j) + " ";
                }
                s += "\n";
                writer.write(s);
            }
            writer.close();

//            sc = new Scanner(System.in);
            sc = new Scanner(new FileReader("in.txt"));
            numRows = sc.nextInt();
            numCols = sc.nextInt();
            for (int i = 0; i < numRows; i++){
                for (int j = 0; j < numCols; j++){
                    grid[i][j] = sc.nextInt();
                }
            }
            findMaxScore();
            System.out.println(maxScore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        Wrapper function of solveGame,
        try to visit each of grid in the first column => column = 0
     */
    public static void findMaxScore(){
        for (int i = 0; i < numRows; i++) {
            if (grid[i][0] == -1)
                continue;
            visited[i][0] = true;
            solveGame(grid[i][0], i, 0);
            visited[i][0] = false;
        }
    }


    public static void solveGame(long sum, int x, int y) {
        //Reach the last column, check and update the maxScore
        if (y == numCols - 1 && sum > maxScore) {
            maxScore = sum;
        }

        //From current cell x, y, there are 3 options for the snake: up, down right
        for (int i = 0; i <= 2; ++i) {
            boolean flag = false; //Indicate whether the snake teleport

            //update x
            int newX = x + increaseX[i];
            if (newX == -1) {
                newX = numRows - 1;
                flag = true;
            }
            if (newX == numRows) {
                newX = 0;
                flag = true;
            }

            //update y
            int newY = y + increaseY[i];
            if (newY == numCols)
                continue;

            //if next cell is blocked or already visited, continue
            if (visited[newX][newY] || grid[newX][newY] == -1)
                continue;

            //Backtracking
            visited[newX][newY] = true;
            if (flag)
                solveGame(grid[newX][newY], newX, newY);
            else
                solveGame(sum + grid[newX][newY], newX, newY);
            visited[newX][newY] = false;
        }
    }
}