package problem_set;

import java.io.File;
import java.util.Scanner;

public class Main {
    private char[][] square;
    private int[][] visit;
    private int n, dfsComponent, counter;
    private boolean isAlive;

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

        int testCase = sc.nextInt();
        for(int current = 1; current <= testCase; current++){
            n = sc.nextInt();
            sc.nextLine();
            square = new char[n][n];
            visit = new int[n][n];

            for (int i = 0; i < n; i++) {
                square[i] = sc.nextLine().trim().toCharArray();
            }

            dfsComponent = 0;
            counter = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (notVisit(i, j)) {
                        isAlive = false;
                        floodfill(i, j, ++dfsComponent);
                        if(isAlive){
                            counter++;
                        }
                    }
                }
            }
            System.out.println(String.format("Case %d: %d", current, counter));
        }
    }

    private boolean notVisit(int i, int j) {
        return (square[i][j] == 'x' || square[i][j] == '@') && visit[i][j] == 0;
    }

    private void floodfill(int row, int col, int component) {
        visit[row][col] = component;
        if(square[row][col] == 'x'){
            isAlive = true;
        }

        for (int i = 0; i < 4; i++) {
            int x = row + dx[i];
            int y = col + dy[i];
            if (0 <= x && x < n && 0 <= y && y < n && notVisit(x, y)) {
                floodfill(x, y, component);
            }
        }
    }

}

