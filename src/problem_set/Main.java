package problem_set;

import java.io.File;
import java.util.Scanner;

public class Main {
    private char[][] square;
    private int[][] visit;
    private int x, y, max, m, n, dfsComponent, currentSum;

    private char land;
    private int[] dx = new int[]{-1, 0, 1, 0};
    private int[] dy = new int[]{0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()) {
            m = sc.nextInt();
            n = sc.nextInt();
            sc.nextLine();
            square = new char[m][n];
            visit = new int[m][n];

            for (int i = 0; i < m; i++) {
                square[i] = sc.nextLine().trim().toCharArray();
            }
            x = sc.nextInt();
            y = sc.nextInt();
            land = square[x][y];

            dfsComponent = 0;
            currentSum = 0;
            max = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (notVisit(i, j)) {
                        currentSum = 0;
                        floodfill(i, j, ++dfsComponent);
                        if (currentSum > max && visit[x][y] != dfsComponent) {
                            max = currentSum;
                        }

                    }
                }
            }
            System.out.println(max);
        }
    }

    private boolean notVisit(int i, int j) {
        return square[i][j] == land && visit[i][j] == 0;
    }

    private void floodfill(int row, int col, int component) {
        visit[row][col] = component;
        currentSum++;

        for (int i = 0; i < 4; i++) {
            int x = row + dx[i];
            int y = col + dy[i];
            if (0 <= x && x < m && 0 <= y && y < n && notVisit(x, y)) {
                floodfill(x, y, component);
            }
        }

        //last col
        if (col == n - 1 && notVisit(row, 0)) {
            floodfill(row, 0, component);
        }

        if (col == 0 && notVisit(row, n - 1)) {
            floodfill(row, n - 1, component);
        }
    }

}

