package problem_set;

import java.util.Scanner;

public class Main {

    private int testCase, n;
    private int[][] values;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        testCase = sc.nextInt();
        for (int route = 1; route <= testCase; route++) {
            n = sc.nextInt();
            values = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    values[i][j] = sc.nextInt();
                    if(i > 0) values[i][j] += values[i-1][j];
                    if(j > 0) values[i][j] += values[i][j -1];
                    if( i > 0 && j > 0) values[i][j] -= values[i-1][j -1];
                }
            }

            int maxSoFar = -100 * 75 * 75;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    int maxEndHere = -100 * 75 * 75;
                    for(int k = i; k < n; k++){
                        for(int h = j; h < n; h++){
                            // from i, j to k, l
                            int sum = values[k][h];
                            if(i > 0) sum -= values[i-1][h];
                            if(j > 0) sum -= values[k][j-1];
                            if(i > 0 && j > 0) sum += values[i-1][j-1];
                            maxEndHere = Math.max(maxEndHere, sum);

                            // top down other
                            int sumTopDown = values[n-1][h];
                            if(j > 0) sumTopDown -= values[n-1][j-1];
                            if(i > 0 || k < n -1) sumTopDown -= sum;
                            maxEndHere = Math.max(maxEndHere, sumTopDown);

                            // left right other
                            int sumLeftRight = values[k][n-1];
                            if(i > 0) sumLeftRight -= values[i-1][n-1];
                            if(j > 0 || h < n -1) sumLeftRight -= sum;
                            maxEndHere = Math.max(maxEndHere, sumLeftRight);

                            // sum 4 corner, ignore case whole sub rectangle
                            if(i == 0 && j == 0 && k == n-1 && h == n-1){
                                // do nothing
                            } else {
                                int sumCorner = values[n-1][n-1];
                                if(i == 0 && k == n -1){
                                    sumCorner -= sumTopDown;
                                }else if(j == 0 && h == n -1){
                                    sumCorner -= sumLeftRight;
                                } else {
                                    sumCorner -= sumTopDown + sumLeftRight + sum;
                                }
                                maxEndHere = Math.max(maxEndHere, sumCorner);
                            }

                        }
                    }
                    maxSoFar = Math.max(maxEndHere, maxSoFar);
                }
            }

            System.out.println(maxSoFar);
        }
    }
}

