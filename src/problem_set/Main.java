package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private int[] visit = new int[110], allocation = new int[110];
    private int  n, k;
    private Map<Integer, List<Integer>> map = new HashMap<>();
    private List<Integer> resultList = new ArrayList<>();
    private static final int BLACK = 1, WHITE = 0;
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//         Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0){
            n = sc.nextInt();
            k = sc.nextInt();

            map.clear();
            resultList.clear();

            for(int i = 1; i <= n; i++){
                visit[i] = 0;
                allocation[i] = -1;
            }

            for(int i = 0; i < k; i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                if(!map.containsKey(first)){
                    map.put(first, new ArrayList<>());
                }
                if(!map.containsKey(second)){
                    map.put(second, new ArrayList<>());
                }
                map.get(first).add(second);
                map.get(second).add(first);
            }

            backTrack(1);

            System.out.println(resultList.size());
            System.out.println(resultList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

    }

    private void backTrack(int current) {
        if(current == n + 1){
            if(getCurrentCount() > resultList.size()){
                updateResult();
            }
            return;
        }

        visit[current] = 1;

        if(isOkToColorBlack(current)){ //can be black/white
            allocation[current] = 1;
            backTrack(current + 1);
        }
        allocation[current] = 0;
        backTrack(current + 1);

        visit[current] = 0;

    }

    private boolean isOkToColorBlack(int node) {
        if(map.containsKey(node)){
            for(int i: map.get(node)){
                if(visit[i] == 1 && allocation[i] == BLACK){
                    return false;
                }
            }
        }
        return true;
    }

    private int getCurrentCount() {
        int counter = 0;
        for(int i = 1; i <= n; i++){
            if(allocation[i] == 1){
                counter++;
            }
        }
        return counter;
    }

    private void updateResult() {
        resultList.clear();
        for(int i = 1; i <= n; i++){
            if(allocation[i] == 1){
                resultList.add(i);
            }
        }
    }
}

