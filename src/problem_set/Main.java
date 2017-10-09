package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private int n;
    private int[] distance, weightList;
    private boolean[] reachList;
    private Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));


        while (true) {
            n = sc.nextInt();
            if (n == -1) {
                break;
            }
            weightList = new int[n + 10];

            for (int i = 1; i <= n; i++) {
                map.put(i, new ArrayList<>());
                int weight = sc.nextInt();
                weightList[i] = weight;
                int numberOfNode = sc.nextInt();
                for (int j = 0; j < numberOfNode; j++) {
                    int next = sc.nextInt();
                    map.get(i).add(next);
                }
            }

            distance = new int[n + 10];
            for (int i = 1; i <= n; i++) {
                distance[i] = Integer.MIN_VALUE;
            }
            distance[1] = 100;

            for (int i = 1; i <= n; i++) {
                for (int node = 1; node <= n; node++) {
                    if(distance[node] <= 0){
                        continue;
                    }
                    for (int neightbor : map.get(node)) {
                        distance[neightbor] = Math.max(distance[neightbor], distance[node] + weightList[neightbor]);
                    }
                }
            }

            boolean cycle = false;
            for (int node = 1; node <= n; node++) {
                if(distance[node] <= 0){
                    continue;
                }
                for (int neightbor : map.get(node)) {
                    if (distance[neightbor] < distance[node] + weightList[neightbor]) {
                        reachList = new boolean[n + 10];
                        if(canReach(node, n)){
                            cycle = true;
                        }
                    }
                }
            }

            boolean result = distance[n] > 0 || cycle;
            System.out.println(result ? "winnable" : "hopeless");
        }
    }

    private boolean canReach(int start, int finish) {
        if(start == finish){
            return true;
        }
        reachList[start] = true;
        if(map.containsKey(start)){
            for(int neighbor: map.get(start)){
                if(!reachList[neighbor] && canReach(neighbor, n)){
                    return true;
                }
            }
        }
        return false;
    }
}

