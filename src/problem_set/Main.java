package problem_set;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private List<Edge> edgeList = new ArrayList<>();
    private int n, m;
    private int[] pset = new int[1010];
    private PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

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
            m = sc.nextInt();
            if (n == 0 && m == 0) {
                break;
            }
            edgeList.clear();
            priorityQueue.clear();
            initSet(n);

            for (int i = 0; i < m; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();
                int weight = sc.nextInt();
                priorityQueue.add(new Edge(first, second, weight));
            }
            while (!priorityQueue.isEmpty()) {
                Edge currentEdge = priorityQueue.poll();
                if (!isSameSet(currentEdge.start, currentEdge.end)) {
                    unionSet(currentEdge.start, currentEdge.end);
                } else {
                    edgeList.add(currentEdge);
                }
            }
            if (edgeList.size() > 0) {
                Collections.sort(edgeList, Comparator.comparingInt(o -> o.weight));
                System.out.println(edgeList.stream().map(edge -> edge.weight).map(String::valueOf)
                                .collect(Collectors.joining(" ")));
            } else {
                System.out.println("forest");
            }
        }
    }

    private void unionSet(int start, int end) {
        pset[findSet(start)] = findSet(end);
    }

    private boolean isSameSet(int start, int end) {
        return findSet(start) == findSet(end);
    }

    private int findSet(int i) {
        return (pset[i] == i ? i : (pset[i] = findSet(pset[i])));
    }

    private void initSet(int n) {
        for (int i = 0; i < n; i++) {
            pset[i] = i;
        }
    }

    static class Edge {
        public final int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}

