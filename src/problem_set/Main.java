package problem_set;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int largeValue = 1000;
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int count = 0;
        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a == 0 && b == 0) {
                break;
            } else {
                List<Edge> list = new ArrayList<>();
                list.add(new Edge(a, b));
                count++;
                while (true) {
                    int c = sc.nextInt();
                    int d = sc.nextInt();
                    if (c == 0 && d == 0) {
                        break;
                    }
                    list.add(new Edge(c, d));
                }

                int[][] array = new int[101][101];
                for (int i = 1; i < 101; i++) {
                    for (int j = 1; j < 101; j++) {
                       array[i][j] = largeValue;
                    }
                }

                for (Edge e : list) {
                    array[e.x][e.y] = 1;
                }
                for (int k = 1; k < 101; k++) {
                    for (int i = 1; i < 101; i++) {
                        for (int j = 1; j < 101; j++) {
                            // only if there is path i to k and k to j
                            array[i][j] = Math.min(array[i][j], array[i][k] + array[k][j]);
                        }
                    }
                }
                int counter = 0;
                int sum = 0;
                for (int i = 1; i < 101; i++) {
                    for (int j = 1; j < 101; j++) {
                        if (i != j && array[i][j] < largeValue) {
                            sum += array[i][j];
                            counter++;
                        }
                    }
                }
                double result = (1.0 * sum)/counter;
                System.out.println(String.format("Case %d: average length between pages = %.3f clicks", count, result));
            }
        }
    }

    static class Edge {
        public final int x, y;

        public Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

