package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] visit, low, dfs_parent;
    private static int  dfsNumberCounter, N, M;
    private static Stack<Integer> stack = new Stack<>();
    private static List<List<Integer>> resultList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        for(int i  = 1; i <= testCase; i++){
            map.clear();
            N = sc.nextInt();
            M = sc.nextInt();
            for(int j = 0; j < M; j++){
                int first = sc.nextInt() - 1;
                int second = sc.nextInt() - 1;
                if(!map.keySet().contains(first)){
                    map.put(first, new ArrayList<>());
                }
                map.get(first).add(second);
            }
            process(i);
        }
    }

    private static void process(int number) {
        visit = new int[N];
        dfsNumberCounter = 0;

        for(int i = 0; i < N; i++){
            if(visit[i] == 0){
                dfsNumberCounter++;
                floodFill(i, dfsNumberCounter);
            }
        }

        System.out.println(String.format("Case %d: %d", number, dfsNumberCounter));
    }

    private static void floodFill(int node, int value) {
        visit[node] = value;
        if(map.keySet().contains(node)){
            for(int i: map.get(node)){
                if(visit[i] == 0){
                    floodFill(i, value);
                }
            }
        }
    }


}

