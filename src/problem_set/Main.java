package problem_set;

import java.util.*;

public class Main {

    private int m, n, totalLen, minTotal;
    private int[] pset = new int[200010];
    private PriorityQueue<Map.Entry<Integer, Map.Entry<Integer, Integer>>> priorityQueue =
                    new PriorityQueue<>(Comparator.comparing(Map.Entry::getKey));

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //         Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (true) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            if (m == 0 && n == 0) {
                break;
            }

            initSet(m);
            priorityQueue.clear();
            totalLen = 0;
            for (int i = 0; i < n; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();
                int weight = sc.nextInt();
                totalLen += weight;
                priorityQueue.add(new AbstractMap.SimpleEntry<>(weight, new AbstractMap.SimpleEntry<>(first, second)));
            }

            kruska();
            System.out.println(totalLen - minTotal);
        }
    }

    private void kruska() {
        minTotal = 0;
        while (!priorityQueue.isEmpty()) {
            Map.Entry<Integer, Map.Entry<Integer, Integer>> entry = priorityQueue.poll();
            Map.Entry<Integer, Integer> pair = entry.getValue();
            if (!isSameSet(pair.getKey(), pair.getValue())) {
                minTotal += entry.getKey();
                unionSet(pair.getKey(), pair.getValue());
            }
        }
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

    private void initSet(int m) {
        for (int i = 0; i < m; i++) {
            pset[i] = i;
        }
    }
}

