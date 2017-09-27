package problem_set;

import java.util.*;

public class Main {

    private int n, r, numberState;
    private double costRoad, costRail;
    private int[] pset = new int[1010];
    private PriorityQueue<Map.Entry<Double, Map.Entry<Integer, Integer>>> priorityQueue =
                    new PriorityQueue<>(Comparator.comparing(Map.Entry::getKey));
    private int[][] pair;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //         Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        for (int current = 1; current <= testCase; current++) {
            n = sc.nextInt();
            r = sc.nextInt();

            initSet(n);
            priorityQueue.clear();
            pair = new int[n + 1][];

            for (int i = 1; i <= n; i++) {
                int[] point = new int[2];
                point[0] = sc.nextInt();
                point[1] = sc.nextInt();
                pair[i] = point;
            }

            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    priorityQueue.add(new AbstractMap.SimpleEntry<>(distance(pair[i], pair[j]),
                                    new AbstractMap.SimpleEntry<>(i, j)));
                }
            }

            kruska();

            System.out.println(String.format("Case #%d: %d %d %d", current, numberState, Math.round(costRoad), Math.round(costRail)));
        }
    }

    private double distance(int[] first, int[] second) {
        return Math.sqrt(Math.pow(Math.abs(first[0] - second[0]), 2) + Math.pow(Math.abs(first[1] - second[1]), 2));
    }

    private void kruska() {
        costRoad = 0.0;
        costRail = 0.0;
        numberState = -1;
        boolean firstTime = false;

        while (!priorityQueue.isEmpty()) {
            Map.Entry<Double, Map.Entry<Integer, Integer>> entry = priorityQueue.poll();
            Map.Entry<Integer, Integer> pair = entry.getValue();
            if (!isSameSet(pair.getKey(), pair.getValue())) {
                double weight = entry.getKey();
                if (weight <= r) {
                    costRoad += weight;
                } else {
                    if (!firstTime) {
                        firstTime = true;
                        numberState = numberOfSet();
                    }
                    costRail += weight;
                }
                unionSet(pair.getKey(), pair.getValue());
            }
        }

        if(n == 1){
            numberState = 1;
        }
        if(numberState < 0){
            numberState = numberOfSet();
        }
    }

    private int numberOfSet() {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(findSet(i));
        }
        return set.size();
    }

    private void unionSet(int i, int j) {
        pset[findSet(i)] = findSet(j);
    }

    private boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    private int findSet(int i) {
        return (pset[i] == i ? i : (pset[i] = findSet(pset[i])));
    }

    private void initSet(int s) {
        for (int i = 1; i <= s; i++) {
            pset[i] = i;
        }
    }
}

