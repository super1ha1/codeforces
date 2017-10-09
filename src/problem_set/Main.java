package problem_set;

import java.util.*;

public class Main {

    private int n, m;
    private int[] distance = new int[1010];
    private Map<Integer, List<Edge>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            map.clear();

            for (int i = 0; i < m; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();
                int weight = sc.nextInt();
                if (!map.containsKey(first)) {
                    map.put(first, new ArrayList<>());
                }
                map.get(first).add(new Edge(second, weight));
            }

            for (int i = 0; i < n; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            distance[0] = 0;

            for (int i = 0; i < n; i++) {
                for (int node = 0; node < n; node++) {
                    if (map.containsKey(node)) {
                        for (Edge neighbor : map.get(node)) {
                            distance[neighbor.end] = Math.min(distance[neighbor.end], distance[node] + neighbor.weight);
                        }
                    }
                }
            }
            boolean possible = false;
            for (int node = 0; node < n; node++) {
                if (map.containsKey(node)) {
                    for (Edge neighbor : map.get(node)) {
                        if (distance[neighbor.end] > distance[node] + neighbor.weight) {
                            possible = true;
                        }
                    }
                }
            }
            System.out.println(possible ? "possible" : "not possible");
        }
    }


    static class Edge {
        public final int end, weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}

