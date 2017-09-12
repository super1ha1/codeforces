package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] visit;
    private static List<Integer> resultList = new ArrayList<>();

    private static int n, m;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            n = sc.nextInt();
            m = sc.nextInt();
            if (n == 0 && m == 0){
                break;
            }
            map.clear();
            resultList.clear();
            for(int i = 0; i < m; i++){
                int first = sc.nextInt() -1;
                int second = sc.nextInt() -1;
                if(!map.keySet().contains(first)){
                    map.put(first, new ArrayList<>());
                }
                map.get(first).add(second);
            }
            process();
        }
    }

    private static void process() {
        visit = new int[n];
        for(int i = 0; i < n; i++){
            if(visit[i] == 0){
                dfs(i);
            }
        }
        Collections.reverse(resultList);
        System.out.println(resultList.stream().map(i -> String.valueOf(i + 1)).collect(Collectors.joining(" ")));
    }

    private static void dfs(int node) {
        visit[node] = 1;
        if(map.keySet().contains(node)){
            for(int i: map.get(node)){
                if(visit[i] == 0){
                    dfs(i);
                }
            }
        }
        resultList.add(node);
    }
}

