package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private final int LARGE_VALUE = Integer.MAX_VALUE;
    private int n;
    private long[] distance, weightList;
    private boolean[] canReachList;
    private Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        //                Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int counter = 0;
        while (sc.hasNext()) {
            n = sc.nextInt();
            counter++;
            weightList = new long[n + 10];
            for (int i = 1; i <= n; i++) {
                weightList[i] = sc.nextInt();
            }

            int r = sc.nextInt();
            map.clear();
            for (int i = 0; i < r; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();
                if (!map.containsKey(first)) {
                    map.put(first, new ArrayList<>());
                }
                map.get(first).add(second);
            }

            bellmanFord();

            int q = sc.nextInt();
            System.out.println(String.format("Set #%d", counter));

            for (int i = 0; i < q; i++) {
                int destination = sc.nextInt();
                canReachList = new boolean[n + 10];
                if(canReach(1, destination)) {
//                    boolean cycle = checkCycle(destination);
//                    if (cycle) {
//                        System.out.println("?");
//                    } else {
//                    }
                    System.out.println(distance[destination] < 3 ? "?" : distance[destination]);

                } else {
                    System.out.println("?");
                }
            }
        }
    }

    private void bellmanFord() {
        distance = new long[n + 10];
        for (int i = 1; i <= n; i++) {
            distance[i] = LARGE_VALUE;
        }
        distance[1] = 0;
        for (int i = 0; i < n; i++) {
            for (int node = 1; node <= n; node++) {
                if (map.containsKey(node)) {
                    for (int neighbor : map.get(node)) {
                        distance[neighbor] = Math.min(distance[neighbor],
                                        distance[node] + (long) Math.pow(weightList[neighbor] - weightList[node], 3));
                    }
                }
            }
        }
    }

    private boolean checkCycle(int destination) {
        boolean cycle = false;
        for (int node = 1; node <= n; node++) {
            if (map.containsKey(node)) {
                for (int neighbor : map.get(node)) {
                    if (distance[neighbor] < distance[node] + (long) Math
                                    .pow(weightList[neighbor] - weightList[node], 3)) {
                        canReachList = new boolean[n + 10];
                        if (canReach(neighbor, destination)) {
                            cycle = true;
                        }
                    }
                }
            }
        }
        return cycle;
    }

    private boolean canReach(int start, int finish) {
        if (start == finish) {
            return true;
        }
        canReachList[start] = true;
        if (map.containsKey(start)) {
            for (int neighbor : map.get(start)) {
                if (!canReachList[neighbor]) {
                    if (canReach(neighbor, finish)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

