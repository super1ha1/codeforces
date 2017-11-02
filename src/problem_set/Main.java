package problem_set;

import java.util.Scanner;

public class Main {

    private static final int largeValue = 1000000;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = 0;
        while (true) {
            int C = sc.nextInt();
            int S = sc.nextInt();
            int Q = sc.nextInt();
            if (C == 0 && S == 0 && Q == 0) {
                break;
            }
            testCase++;
            if (testCase > 1) {
                System.out.println("");
            }
            int[][] array = new int[C + 1][C + 1];
            for (int i = 1; i <= C; i++) {
                for (int j = 1; j <= C; j++) {
                    array[i][j] = largeValue;
                }
            }
            for (int i = 0; i < S; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();
                int loud = sc.nextInt();
                if (first <= C && second <= C && first > 0 && second > 0) {
                    array[first][second] = loud;
                    array[second][first] = loud;
                }
            }

            for (int k = 1; k <= C; k++) {
                for (int i = 1; i <= C; i++) {
                    for (int j = 1; j <= C; j++) {
                        array[i][j] = Math.min(array[i][j], Math.max(array[i][k], array[k][j]));
                    }
                }
            }

            System.out.println("Case #" + testCase);
            for (int i = 0; i < Q; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();
                if (first <= C && second <= C && first > 0 && second > 0) {
                    if (array[first][second] < largeValue) {
                        System.out.println(array[first][second]);
                    } else {
                        System.out.println("no path");
                    }
                } else {
                    System.out.println("no path");
                }
            }
        }
    }
}

