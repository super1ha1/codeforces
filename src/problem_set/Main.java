package problem_set;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static int[][] result;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        int testCase = sc.nextInt();
        for(int i = 0; i < testCase; i++){
            result = new int[1025][1025];
            int d = sc.nextInt();
            int n = sc.nextInt();
            for(int j = 0; j < n; j++){
                int x  = sc.nextInt();
                int y = sc.nextInt();
                int size = sc.nextInt();
                update(result, x, y,  size, d);
            }
            process(result);
        }
    }

    private static void process(int[][] result) {
        int maxValue = -1;
        int currentX = -1, currentY = -1;
        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[0].length; j++){
                if(result[i][j] > maxValue){
                    maxValue = result[i][j];
                    currentX = i;
                    currentY = j;
                }
            }
        }
        System.out.println(currentX + " " + currentY + " " + maxValue);
    }

    private static void update(int[][] result, int x, int y, int size, int d) {
        int lowX = x - d >= 0 ? x - d : 0;
        int highX = x + d <= 1024 ? x + d : 1024;
        int lowY = y - d >= 0 ? y - d : 0;
        int highY = y + d <= 1024 ? y + d : 1024;
        for(int i = lowX; i <= highX ; i++){
            for(int j = lowY; j <= highY; j ++){
                result[i][j] += size;
            }
        }

    }

}

