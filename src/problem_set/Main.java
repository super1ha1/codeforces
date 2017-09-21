package problem_set;

import java.util.*;

public class Main {

    private int n, m, minTotal, costAirPort, numberAirPort;
    private int[] pset = new int[200010];
    private PriorityQueue<Map.Entry<Integer, Map.Entry<Integer, Integer>>> priorityQueue =
                    new PriorityQueue<>(Comparator.comparing(Map.Entry::getKey));

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //         Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        for(int current = 1; current <= testCase; current++) {
            n = sc.nextInt();
            m = sc.nextInt();
            costAirPort = sc.nextInt();

            initSet(n);
            priorityQueue.clear();

            for (int i = 0; i < m; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();
                int weight = sc.nextInt();
                priorityQueue.add(new AbstractMap.SimpleEntry<>(weight, new AbstractMap.SimpleEntry<>(first, second)));
            }

            kruska();

            System.out.println(String.format("Case #%d: %d %d", current, minTotal, numberAirPort));
        }
    }

    private void kruska() {
        minTotal = 0;
        while (!priorityQueue.isEmpty()) {
            Map.Entry<Integer, Map.Entry<Integer, Integer>> entry = priorityQueue.poll();
            Map.Entry<Integer, Integer> pair = entry.getValue();
            if (!isSameSet(pair.getKey(), pair.getValue()) && entry.getKey() < costAirPort) {
                minTotal += entry.getKey();
                unionSet(pair.getKey(), pair.getValue());
            }
        }

        numberAirPort = numberOfSet();
        minTotal += numberAirPort * costAirPort;
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

