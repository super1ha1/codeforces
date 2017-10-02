package problem_set;

import java.util.*;

public class Main {

    private int n, m;
    private int[][] distance = new int[1000][1000], weight = new int[1000][1000], visit = new int[1000][1000];
    private PriorityQueue<Point> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

    private int[] dx = new int[] {-1, 0, 1, 0};
    private int[] dy = new int[] {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    weight[i][j] = sc.nextInt();
                    distance[i][j] = Integer.MAX_VALUE;
                    visit[i][j] = 0;
                }
            }

            distance[0][0] = weight[0][0];
            priorityQueue.clear();
            priorityQueue.add(new Point(0, 0, distance[0][0]));

            while (!priorityQueue.isEmpty()) {
                Point currentPoint = priorityQueue.poll();
                int entryDistance = currentPoint.weight;
                visit[currentPoint.x][currentPoint.y] = 1;

                if (distance[currentPoint.x][currentPoint.y] == entryDistance) {
                    for (int i = 0; i < 4; i++) {
                        int nextX = currentPoint.x + dx[i];
                        int nextY = currentPoint.y + dy[i];
                        if (0 <= nextX && nextX < n && 0 <= nextY && nextY < m) {

                            if (entryDistance + weight[nextX][nextY] < distance[nextX][nextY]) {
                                distance[nextX][nextY] = entryDistance + weight[nextX][nextY];
                                if(visit[nextX][nextY] == 0){
                                    priorityQueue.add(new Point(nextX, nextY, distance[nextX][nextY]));
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(distance[n - 1][m - 1]);
        }
    }

    static class Point {
        public final int x;
        public final int y;
        public final int weight;

        public Point(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

}

