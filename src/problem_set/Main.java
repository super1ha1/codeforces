package problem_set;

import java.util.*;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static boolean[] articulation;
    private static int[] dfs_num, dfs_low, dfs_parent;
    private static int root, numChildren, n, dfsNumberCounter;
    private static List<Map.Entry<Integer, Integer>> bridgeList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (true){
            n = sc.nextInt();
            if(n == 0){
                break;
            }
            sc.nextLine();
            map.clear();
            List<String> list = new ArrayList<>();

            while (true){
                String line = sc.nextLine().trim();
                if(line.equalsIgnoreCase("0")){
                    break;
                } else {
                    list.add(line);
                }
            }
            process(list);
        }
    }

    private static void process(List<String> list) {
        for(String line: list){
            String[] strings = line.split(" ");
            int base = Integer.valueOf(strings[0]) - 1;
            if(!map.keySet().contains(base)){
                map.put(base, new ArrayList<>());
            }
            for(int i = 1; i < strings.length; i++){
                int current = Integer.valueOf(strings[i]) - 1;
                if(!map.keySet().contains(current)){
                    map.put(current, new ArrayList<>());
                }
                map.get(base).add(current);
                map.get(current).add(base);
            }
        }

        dfs_low = new int[n];
        dfs_num = new int[n];
        dfs_parent = new int[n];
        articulation = new boolean[n];
        dfsNumberCounter = 1;

        for (int i: map.keySet()){
            if(dfs_num[i] == 0){
                root = i;
                numChildren = 0;
                articulationPointAndBridge(i);
                articulation[i] = (numChildren > 1);
            }
        }

        int counter = 0;
        for(int i = 0; i < n; i++){
            if(articulation[i]){
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static void articulationPointAndBridge(int node) {
        dfs_num[node] = dfsNumberCounter;
        dfs_low[node] = dfsNumberCounter;
        dfsNumberCounter++;

        for(int i: map.get(node)){
            if(dfs_num[i] == 0){ //not visit yet
                dfs_parent[i] = node;

                if(node == root){
                    numChildren++;
                }

                articulationPointAndBridge(i);

                //check articulation point
                if(dfs_low[i]  >= dfs_num[node]){
                    articulation[node] = true;
                }

                //check bridge
                if(dfs_low[i]  > dfs_num[node]){
                    bridgeList.add(new AbstractMap.SimpleEntry<>(node, i));
                }

                //update low
                dfs_low[node] = Math.min(dfs_low[i], dfs_low[node]);

            } else { //already visit
                if(dfs_parent[node] != i){
                    dfs_low[node] = Math.min(dfs_num[i], dfs_low[node]);
                }
            }
        }
    }
}

