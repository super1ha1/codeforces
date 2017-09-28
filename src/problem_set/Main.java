package problem_set;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private int n;
    private Map<Integer, List<Map.Entry<Integer, Integer>>> map = new HashMap<>();
    private int[] distance;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int region = 0;
        while (true) {
            n = sc.nextInt();
            if (n == 0) {
                break;
            }
            region++;
            map.clear();

            for (int i = 1; i <= n; i++) {
                if (!map.containsKey(i)) {
                    map.put(i, new ArrayList<>());
                }

                int numberNode = sc.nextInt();
                for (int j = 0; j < numberNode; j++) {
                    int nextPoint = sc.nextInt();
                    int distance = sc.nextInt();
                    map.get(i).add(new AbstractMap.SimpleEntry<>(distance, nextPoint));
                }
            }
            sc.nextLine();
            String line = sc.nextLine();

            String[] array = line.trim().split("[\\s]+");
            int start = Integer.valueOf(array[0]);
            int end = Integer.valueOf(array[1]);

            if (start == end) {
                List<Integer> pathList = new ArrayList<>();
                pathList.add(start);
                System.out.println(String.format("Case %d: Path = %s; %d second delay", region,
                                pathList.stream().map(String::valueOf).collect(Collectors.joining(" ")), 0));
            } else {
                distance = new int[n + 1];
                for (int i = 0; i <= n; i++) {
                    distance[i] = Integer.MAX_VALUE;
                }
                distance[start] = 0;

                PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue =
                                new PriorityQueue<>(Comparator.comparing(Map.Entry::getKey));
                priorityQueue.add(new AbstractMap.SimpleEntry<>(0, start));
                Map<Integer, Integer> parentMap = new HashMap<>();

                while (!priorityQueue.isEmpty()) {
                    Map.Entry<Integer, Integer> entry = priorityQueue.poll();
                    int weight = entry.getKey();
                    int vertex = entry.getValue();

                    if (weight == distance[vertex]) {
                        if (map.containsKey(vertex)) {
                            for (Map.Entry<Integer, Integer> neighbor : map.get(vertex)) {
                                int edgeWeight = neighbor.getKey();
                                int neighborVertex = neighbor.getValue();
                                if (edgeWeight + distance[vertex] < distance[neighborVertex]) {
                                    distance[neighborVertex] = edgeWeight + distance[vertex];
                                    priorityQueue.add(new AbstractMap.SimpleEntry<>(distance[neighborVertex],
                                                    neighborVertex));
                                    parentMap.put(neighborVertex, vertex);
                                }
                            }
                        }
                    }
                }

                List<Integer> pathList = new ArrayList<>();
                pathList.add(end);
                int nextParent = parentMap.get(pathList.get(pathList.size() - 1));
                while (nextParent != start) {
                    pathList.add(nextParent);
                    nextParent = parentMap.get(pathList.get(pathList.size() - 1));
                }
                pathList.add(start);
                Collections.reverse(pathList);
                System.out.println(String.format("Case %d: Path = %s; %d second delay", region,
                                pathList.stream().map(String::valueOf).collect(Collectors.joining(" ")),
                                distance[end]));
            }
        }
    }

}

