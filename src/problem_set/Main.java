package problem_set;

import java.util.Scanner;

public class Main {

    private int n;
    private int[][] values;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()) {
            n = sc.nextInt();
            values = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    values[i][j] = sc.nextInt();
                    if (i > 0)
                        values[i][j] += values[i - 1][j];
                    if (j > 0)
                        values[i][j] += values[i][j - 1];
                    if (i > 0 && j > 0)
                        values[i][j] -= values[i - 1][j - 1];
                }
            }
            int maxSoFar = -127 * 100 * 100;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int maxEndHere = -127 * 100 * 100;
                    for (int k = i; k < n; k++) {
                        for (int l = j; l < n; l++) {
                            int maxLocal = values[k][l];
                            if (i > 0)
                                maxLocal -= values[i - 1][l];
                            if (j > 0)
                                maxLocal -= values[k][j - 1];
                            if (i > 0 && j > 0)
                                maxLocal += values[i - 1][j - 1];
                            maxEndHere = Math.max(maxEndHere, maxLocal);
                        }
                    }
                    maxSoFar = Math.max(maxSoFar, maxEndHere);
                }
            }
            System.out.println(maxSoFar);
        }
    }
}

