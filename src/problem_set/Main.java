package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int numComponent, m, n;
    private static int[][] array;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            m = sc.nextInt();
            if(m == 0){
                break;
            }
            n = sc.nextInt();
            sc.nextLine();

            array = new int[m][n];
            for(int i = 0; i < m; i++){
                String line = sc.nextLine();
                for(int j = 0; j < n; j++){
                    if(line.charAt(j) == '@'){
                        array[i][j] = 0;
                    }else {
                        array[i][j] = -1;
                    }
                }
            }

            numComponent = 0;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(array[i][j] == 0){
                        floodfill(i, j, ++numComponent);
                    }
                }
            }

            System.out.println(numComponent);

        }
    }

    private static void floodfill(int row, int col, int color) {
        array[row][col] = color;

        //neigh bor
        int lowX = row - 1 >= 0 ? row - 1 : 0;
        int lowY = col - 1 >= 0 ? col - 1 : 0;
        int highX = row + 1  <= m - 1 ? row + 1 : m -1;
        int highY = col + 1 <= n - 1 ? col + 1 : n -1;

        for(int i = lowX; i <= highX; i++){
            for(int j = lowY; j <= highY; j++){
                if(array[i][j] == 0){
                    floodfill(i, j, numComponent);
                }
            }
        }
    }

}

