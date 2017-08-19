package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static int numComponent, n, sum;
    private static int[][] array;
    private static char[][] chars;
    private static List<String> queryList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            n = sc.nextInt();
            if(n == 0){
                break;
            }
            if(n == 1){
                System.out.println("good");
                continue;
            }

            sc.nextLine();
            array = new int[n][n];

            for(int i = 1; i < n; i++){
                String line = sc.nextLine().trim();
                String[] lines = line.split(" ");
                for(int j = 0; j < lines.length - 1; j += 2){
                    int first = Integer.valueOf(lines[j]);
                    int second = Integer.valueOf(lines[j + 1]);
                    array[first - 1][second -1] = i;
                }
            }

            if(process()){
                System.out.println("good");
            }else {
                System.out.println("wrong");
            }
        }
    }

    private static boolean process() {
        for(int partition = 0; partition < n; partition++){
            numComponent = n + partition;
            sum = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(array[i][j] == partition){
                        floodfill(i, j, ++numComponent, partition);
                    }
                }
            }
            if(numComponent > n + partition + 1){
                return false;
            }
            if(sum != n){
                return false;
            }
        }
        return true;
    }

    private static void floodfill(int row, int col, int color, int partition) {
        array[row][col] = color;
        sum++;

        int lowX = row -1 >= 0 ? row -1 : 0;
        int lowY = col -1 >= 0 ? col -1 : 0;
        int highX = row + 1 <= n -1 ? row + 1 : n -1;
        int highY = col + 1 <= n -1 ? col + 1 : n -1;

        //same row
        for(int i = lowX; i <= highX; i++){
            if(array[i][col] == partition){
                floodfill(i, col, color, partition);
            }
        }

        //same colmn
        for(int j = lowY; j <= highY; j++){
            if(array[row][j] == partition){
                floodfill(row, j, color, partition);
            }
        }
    }

}

