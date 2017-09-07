package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static Map<Integer, Set<Integer>> map = new HashMap<>();
    private static int[] visit, low, parent, backEdge;
    private static int  dfsNumberCounter, N, M;
    private static Stack<Integer> stack = new Stack<>();
    private static List<List<Integer>> resultList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

//        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        for(int i  = 1; i <= testCase; i++){
            map.clear();
            N = sc.nextInt();
            M = sc.nextInt();
            for(int j = 0; j < M; j++) {
                int first = sc.nextInt() - 1;
                int second = sc.nextInt() - 1;
                if(!map.keySet().contains(first)){
                    map.put(first, new HashSet<>());
                }
                map.get(first).add(second);
            }
            process(i);
        }
    }

    private static void process(int number) {
        visit = new int[N];
        parent = new int[N];
        backEdge = new int[N];
        dfsNumberCounter = 0;

        for(int i = 0; i < N; i++){
            if(visit[i] == 0){
                dfsNumberCounter++;
                floodFill(i, dfsNumberCounter, -1);
            }
        }

        int counter = 0;
        for(int i = 0; i < N; i++){
            if(parent[i] == -1){
                counter++;
            }
        }

        System.out.println(String.format("Case %d: %d", number, counter));
    }

    private static void floodFill(int node, int value, int parentNode) {
        visit[node] = value;
        parent[node] = parentNode;

        if(map.keySet().contains(node)){
            for(int i: map.get(node)){
                if(visit[i] == 0){
                    floodFill(i, value, node);
                }else {
                    //already visit: 3 case: forward, cross, back
                    if(visit[i] == visit[node]){ //same tree
                        //ignore
                        backEdge[i] = 1;
                    }else if(visit[i] != visit[node]){ //diff tree
                        if(backEdge[i] == 1){
                            floodFill(i, value, node);
                        }
                    }
                }
            }
        }
    }


}

