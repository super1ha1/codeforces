package problem_set;

import java.util.*;

public class Main {

    private int s, c, minTotal, start;
    private String startStation;
    private int[] pset = new int[200010];
    private PriorityQueue<Map.Entry<Integer, Map.Entry<Integer, Integer>>> priorityQueue =
                    new PriorityQueue<>(Comparator.comparing(Map.Entry::getKey));
    private Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //         Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (true) {
            s = sc.nextInt();
            c = sc.nextInt();
            if (s == 0 && c == 0) {
                break;
            }
            sc.nextLine();

            initSet(s);
            priorityQueue.clear();
            map.clear();

            for (int i = 0; i < s; i++) {
                String station = sc.nextLine().trim();
                map.put(station, i);
            }

            for (int i = 0; i < c; i++) {
                String[] array = sc.nextLine().trim().split("[\\s]+");
                int first = map.get(array[0]);
                int second = map.get(array[1]);
                int weight = Integer.parseInt(array[2]);
                priorityQueue.add(new AbstractMap.SimpleEntry<>(weight, new AbstractMap.SimpleEntry<>(first, second)));
            }

            startStation = sc.nextLine();
            if (map.containsKey(startStation)) {
                start = map.get(startStation);
                kruska();
                if (allConnect()) {
                    System.out.println(minTotal);
                } else {
                    System.out.println("Impossible");
                }
            } else {
                System.out.println("Impossible");
            }
        }
    }

    private boolean allConnect() {
        for (int i = 0; i < s; i++) {
            if (findSet(i) != findSet(start)) {
                return false;
            }
        }
        return true;
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

    private void initSet(int s) {
        for (int i = 0; i < s; i++) {
            pset[i] = i;
        }
    }
}

