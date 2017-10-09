package problem_set;

import java.util.*;

public class Main {

    private int n, start, finish;
    private int[] distance, weightList;
    private Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (true) {
            n = sc.nextInt();
            if (n == -1) {
                break;
            }

            weightList = new int[n + 10];
            map.clear();

            for (int i = 1; i <= n; i++) {
                int weight = sc.nextInt();
                weightList[i] = -1 * weight;

                if (!map.containsKey(i)) {
                    map.put(i, new ArrayList<>());
                }
                int numberOfNode = sc.nextInt();
                for (int j = 0; j < numberOfNode; j++) {
                    int next = sc.nextInt();
                    map.get(i).add(next);
                }

                if (weight == 0) {
                    if (numberOfNode > 0) {
                        start = i;
                    } else if (numberOfNode == 0) {
                        finish = i;
                    }
                }
            }

            distance = new int[n + 10];
            for (int i = 1; i <= n; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            distance[start] = -100;

            for (int i = 1; i <= n; i++) {
                for (int node = 1; node <= n; node++) {
                    if (map.containsKey(node)) {
                        for (int neighbor : map.get(node)) {
                            distance[neighbor] = Math.min(distance[neighbor], distance[node] + weightList[neighbor]);
                        }
                    }
                }
            }

            boolean possible = false;
            if (distance[finish] < 0) {
                possible = true;
            } else {
                for (int node = 1; node <= n; node++) {
                    if (map.containsKey(node)) {
                        for (int neighbor : map.get(node)) {
                            if (distance[neighbor] > distance[node] + weightList[neighbor]) {
                                possible = true;
                            }
                        }
                    }
                }
            }
            if(n == 0){
                possible = false;
            }
            if(n == 1){
                possible = true;
            }
            System.out.println(possible ? "winnable" : "hopeless");
        }
    }
}

