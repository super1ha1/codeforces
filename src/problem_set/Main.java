package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static Map<Integer, Set<Integer>> map = new HashMap<>();
    private static int[] visit, appear, parent;
    private static int  dfsNumberCounter, N, M, visitCounter;
    private static Set<Integer> resultSet = new HashSet<>();

    public static void main(String[] args) throws Exception {

//        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        for(int i  = 1; i <= testCase; i++){
            map.clear();
            resultSet.clear();
            N = sc.nextInt();
            M = sc.nextInt();

            appear= new int[N];
            for(int j = 0; j < M; j++) {
                int first = sc.nextInt() - 1;
                int second = sc.nextInt() - 1;
                appear[first] = 1;
                appear[second] = 1;

                if(!map.keySet().contains(second)){
                    map.put(second, new HashSet<>());
                }
                if(second != first){
                    map.get(second).add(first);
                }
            }
            process(i);
        }
    }

    private static void process(int number) {
        visit = new int[N];
        dfsNumberCounter = 0;
        visitCounter = 0;

        for(int i = 0; i < N; i++){
            if(appear[i] == 0){
                resultSet.add(i);
            }
        }

        for(int i = 0; i < N; i++){
            if(visit[i] == 0){
                visitCounter++;
                floodFill(i, visitCounter);
            }
        }

        System.out.println(String.format("Case %d: %d", number, resultSet.size() > 0 ? resultSet.size() : 1));
    }

    private static void floodFill(int node, int value) {
        visit[node] = value;

        if(map.keySet().contains(node) && map.get(node).size() > 0){
            for(int i: map.get(node)){
                if(visit[i] == 0){
                    floodFill(i, value);
                }
            }
        }else {
            dfsNumberCounter++;
            resultSet.add(node);
        }
    }


}

