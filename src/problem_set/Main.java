package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private int n, dfsComponent;
    private List<Edge> resultList = new ArrayList<>();
    private Map<Integer, Set<Integer>> map = new HashMap<>();
    private int[] dfsLow, dfsNum, dfsParent;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
//                Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()) {
            n = sc.nextInt();
            sc.nextLine();
            map.clear();

            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                String[] array = line.split("[\\s]+");
                int first = Integer.valueOf(array[0]);
                if (!map.containsKey(first)) {
                    map.put(first, new HashSet<>());
                }
                // parse (1)
                int num = Integer.valueOf(String.valueOf(array[1].charAt(1)));

                for (int j = 0; j < num; j++) {
                    int second = Integer.valueOf(array[2 + j]);
                    if (!map.containsKey(second)) {
                        map.put(second, new HashSet<>());
                    }
                    map.get(first).add(second);
                    map.get(second).add(first);
                }
            }

            resultList.clear();
            dfsLow = new int[n];
            dfsNum = new int[n];
            dfsParent = new int[n];
            dfsComponent = 0;

            for (int i = 0; i < n; i++) {
                if (dfsNum[i] == 0) {
                    articulationPoint(i);
                }
            }
            System.out.println(String.format("%d critical links", resultList.size()));
            Collections.sort(resultList, (Comparator.comparingInt(o -> o.start)));
            for(Edge edge: resultList){
                System.out.println(String.format("%d - %d", edge.start, edge.end));
            }
            System.out.println("");
        }

    }

    private void articulationPoint(int node) {
        dfsComponent++;
        dfsNum[node] = dfsComponent;
        dfsLow[node] = dfsComponent;

        if (map.containsKey(node)) {
            for (int neighbor : map.get(node)) {
                if (dfsNum[neighbor] == 0) {
                    dfsParent[neighbor] = node;
                    articulationPoint(neighbor);

                    if (dfsLow[neighbor] > dfsNum[node]) {
                        resultList.add(new Edge(Math.min(node, neighbor), Math.max(node, neighbor)));
                    }

                    dfsLow[node] = Math.min(dfsLow[node], dfsLow[neighbor]);

                } else if (dfsParent[node] != neighbor) {
                    dfsLow[node] = Math.min(dfsLow[node], dfsNum[neighbor]);
                }
            }
        }
    }

    static class Edge {
        public final int start, end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

